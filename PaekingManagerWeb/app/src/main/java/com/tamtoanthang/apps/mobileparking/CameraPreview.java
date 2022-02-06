package com.tamtoanthang.apps.mobileparking;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by lue on 16-11-2016.
 */
public class CameraPreview  extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = "Preview";
    boolean isPreviewRunning;
    SurfaceHolder mHolder;
    public Camera camera;



    public CameraPreview(Context c) {
        super(c);
        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
         isPreviewRunning = true;
    }

    public void surfaceCreated(SurfaceHolder holder) {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.CAMERA}, 50);
        else {
            try {
                if(camera==null) {
                    camera = Camera.open();
                    Camera.Parameters parameters = camera.getParameters();
                    parameters.set("jpeg-quality", 70);
                    parameters.setPictureFormat(PixelFormat.JPEG);
                    parameters.setPictureSize(320, 240);
                    camera.setParameters(parameters);
                }
                camera.setPreviewDisplay(holder);
                camera.setPreviewCallback(new PreviewCallback() {
                    public void onPreviewFrame(byte[] data, Camera arg1) {
                        arg1.setDisplayOrientation(90);

                        FileOutputStream outStream = null;
                        try {
                            outStream = new FileOutputStream(String.format("", System.currentTimeMillis()));
                            outStream.close();
                            Log.d(TAG, "onPreviewFrame - wrote bytes: " + data.length);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                        }
                        CameraPreview.this.invalidate();


                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // Now that the size is known, set up the camera parameters and begin
        // the preview.

        if (mHolder.getSurface() == null){
            // preview surface does not exist
            return;
        }

        // stop preview before making changes
        try {
            camera.stopPreview();
        } catch (Exception e){
            // ignore: tried to stop a non-existent preview
        }
        camera.setDisplayOrientation(90);

        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();

        } catch (Exception e) {
            // intentionally left blankimage for a test
        }
    }
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (camera != null) {
            camera.stopPreview();

            camera.release();

            camera = null;
        }
    }



    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint p= new Paint(Color.RED);
        Log.d(TAG,"draw");
        canvas.drawText("PREVIEW", canvas.getWidth()/2, canvas.getHeight()/2, p );
    }

}


