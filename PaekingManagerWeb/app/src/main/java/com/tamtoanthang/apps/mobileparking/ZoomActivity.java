package com.tamtoanthang.apps.mobileparking;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.tamtoanthang.apps.mobileparking.R;

public class ZoomActivity extends Activity {

    ImageView imageDetail; Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();
    PointF startPoint = new PointF();
    PointF midPoint = new PointF(); float oldDist = 1f; static final int NONE = 0;
    static final int DRAG = 1; static final int ZOOM = 2; int mode = NONE;
    /** Called when the activity is first created. */
    @Override public void onCreate(Bundle savedInstanceState)
    { super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoomactivity);
        imageDetail = (ImageView) findViewById(R.id.imageViewdetail);
        Intent intent = getIntent();
        Bundle ex = getIntent().getExtras();
        Bitmap bmp2 = ex.getParcelable("inimage");
        imageDetail.setImageBitmap(bmp2);
        imageDetail.setOnTouchListener(new View.OnTouchListener()
        {
            @Override public boolean onTouch(View v, MotionEvent event)
        { ImageView view = (ImageView) v; System.out.println("matrix=" + savedMatrix.toString());
            switch (event.getAction() & MotionEvent.ACTION_MASK)
            { case MotionEvent.ACTION_DOWN:
                savedMatrix.set(matrix); startPoint.set(event.getX(), event.getY()); mode = DRAG;
                break; case MotionEvent.ACTION_POINTER_DOWN: oldDist = spacing(event); if (oldDist > 10f)
            { savedMatrix.set(matrix); midPoint(midPoint, event); mode = ZOOM; }
                break;
                case MotionEvent.ACTION_UP: case MotionEvent.ACTION_POINTER_UP: mode = NONE;
                break;
                case MotionEvent.ACTION_MOVE: if (mode == DRAG) { matrix.set(savedMatrix);
                matrix.postTranslate(event.getX() - startPoint.x, event.getY() - startPoint.y);
            } else if
                    (mode == ZOOM)
            {
                float newDist = spacing(event); if (newDist > 10f) { matrix.set(savedMatrix);
                float scale = newDist / oldDist; matrix.postScale(scale, scale, midPoint.x, midPoint.y); }
            }
                break;
            }
            view.setImageMatrix(matrix);
            return true; }
            @SuppressLint("FloatMath") private float spacing(MotionEvent event)
            {
                float x = event.getX(0) - event.getX(1); float y = event.getY(0) - event.getY(1);
                return (float) Math.sqrt(x * x + y * y);
            } private void midPoint(PointF point, MotionEvent event)
        { float x = event.getX(0) + event.getX(1);
            float y = event.getY(0) + event.getY(1);
            point.set(x / 2, y / 2); } });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        super.onWindowFocusChanged(hasFocus);

        float imageWidth = imageDetail.getDrawable().getIntrinsicWidth();
        float imageHeight = imageDetail.getDrawable().getIntrinsicHeight();
        RectF drawableRect = new RectF(0, 0, imageWidth, imageHeight);
        RectF viewRect = new RectF(0, 0, imageDetail.getWidth(),
                imageDetail.getHeight());
        Matrix matrix = imageDetail.getMatrix();
        matrix.setRectToRect(drawableRect, viewRect, Matrix.ScaleToFit.CENTER);
        imageDetail.setImageMatrix(matrix);
        imageDetail.invalidate();

    }
}