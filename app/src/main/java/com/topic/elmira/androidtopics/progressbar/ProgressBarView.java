package com.topic.elmira.androidtopics.progressbar;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.topic.elmira.androidtopics.R;

/**
 * Created by Elmira Andreeva on 6/12/18.
 */

public class ProgressBarView extends View {

    LayerDrawable layerDrawable;

    ScaleDrawable progressLayer;
    ScaleDrawable secondProgressLayer;
    Drawable backgroundLayer;

    int width;
    int height;

    private float progress;

    public ProgressBarView(Context context) {
        super(context);
        init(context);
    }

    public ProgressBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ProgressBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ProgressBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;

        backgroundLayer.setBounds(0, 0, width, height);
        layerDrawable.setBounds(0, 0, width, height);

        secondProgressLayer.setLevel(0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        progressLayer.setLevel((int) progress);
        //secondProgressLayer.setLevel(10000 - (int) progress);

        layerDrawable.draw(canvas);
    }

    private void init(Context context) {
        layerDrawable = (LayerDrawable) context.getDrawable(R.drawable.custom_progress_bar);

        progressLayer = (ScaleDrawable) layerDrawable.getDrawable(2);
        secondProgressLayer = (ScaleDrawable) layerDrawable.getDrawable(1);
        backgroundLayer = layerDrawable.findDrawableByLayerId(android.R.id.background);

        animateProgress();
    }

    public void setProgress(float value) {
        this.progress = value;
        invalidate();
    }

    private void animateProgress() {
        ObjectAnimator progressAnimator = ObjectAnimator.ofFloat(this, "progress", 0, 10000);
        progressAnimator.setInterpolator(new LinearInterpolator());
        progressAnimator.setDuration(4000);
        progressAnimator.start();
    }
}
