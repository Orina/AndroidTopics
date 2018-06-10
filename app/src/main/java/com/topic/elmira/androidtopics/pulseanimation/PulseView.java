package com.topic.elmira.androidtopics.pulseanimation;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Elmira on 6/9/2018.
 */

public class PulseView extends View {

    private float mRadius;
    private Paint mPaint;
    public static final int COLOR_ADJUSTER = 20;

    public PulseView(Context context) {
        super(context);
        init(context);
    }

    public PulseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public void setRadius(float radius){
        this.mRadius = radius;
        invalidate();
    }

    private void init(Context context){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }
}
