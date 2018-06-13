package com.topic.elmira.androidtopics.drawonimage;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by Elmira Andreeva on 6/12/18.
 */

public class CustomDrawableView extends AppCompatImageView {

    public CustomDrawableView(Context context) {
        super(context);
    }

    public CustomDrawableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomDrawableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
