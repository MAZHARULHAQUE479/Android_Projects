package company.cell.com.Cellotto.Custom;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.util.AttributeSet;

import com.rengwuxian.materialedittext.MaterialEditText;

import company.cell.com.Cellotto.R;

public class MyEditTextLight extends MaterialEditText {

    private static final int INVALID_VALUE = -1;
    public MyEditTextLight(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setUpController(context, attrs);
    }

    public MyEditTextLight(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        setUpController(context, attrs);
    }
    public MyEditTextLight(Context context) {
        super(context);
    }



    private void setUpController(Context context,AttributeSet attrs) {
        if (!isInEditMode()) {
        }

        setFocusFraction(1f);
        setFloatingLabel(FLOATING_LABEL_NORMAL);
        setFloatingLabelTextColor(getResources().getColor(R.color.textColorLightPrimary));
        setBaseColor(getResources().getColor(R.color.textColorLightPrimary));
        setTextColor(getResources().getColor(R.color.textColorLightPrimary));
        setHintTextColor(getResources().getColor(R.color.textColorLightPrimary));
        setPrimaryColor(getResources().getColor(R.color.colorLightAccent));
        setErrorColor(getResources().getColor(R.color.colorLightAccent));
        setHelperTextColor(getResources().getColor(R.color.textColorLightPrimary));
        setUnderlineColor(getResources().getColor(R.color.textColorLightPrimary));

    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);


    }
}