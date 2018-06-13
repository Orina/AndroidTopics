package com.topic.elmira.androidtopics.pulseanimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.Random;

/**
 * Created by Elmira on 6/9/2018.
 */

public class PulseView extends View {

    private float mRadius;
    private Paint mPaint;

    private float mX;
    private float mY;

    private AnimatorSet mPulseAnimatorSet;
    public static final int DURATION = 400;
    public static final int DELAY = 200;

    private Random random = new Random(System.currentTimeMillis());

    public PulseView(Context context) {
        super(context);
        init(context);
    }

    public PulseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        ObjectAnimator growAnimator = ObjectAnimator.ofFloat(this, "radius", 0, w / 2);
        growAnimator.setDuration(DURATION);
        growAnimator.setInterpolator(new LinearInterpolator());

        ObjectAnimator shrinkAnimator = ObjectAnimator.ofFloat(this, "radius", w / 2, 0);
        shrinkAnimator.setStartDelay(DELAY);
        shrinkAnimator.setInterpolator(new LinearOutSlowInInterpolator());
        shrinkAnimator.setDuration(DURATION);

        ObjectAnimator repeatAnimator = ObjectAnimator.ofFloat(this, "radius", 0, w / 2);
        repeatAnimator.setStartDelay(DELAY);
        repeatAnimator.setDuration(DURATION);
        repeatAnimator.setRepeatCount(1);
        repeatAnimator.setRepeatMode(ValueAnimator.REVERSE);

        mPulseAnimatorSet.play(growAnimator).before(shrinkAnimator);
        mPulseAnimatorSet.play(repeatAnimator).after(shrinkAnimator);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mX, mY, mRadius, mPaint);
    }

    public void setRadius(float radius) {
        this.mRadius = radius;
        invalidate();
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        mPulseAnimatorSet = new AnimatorSet();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mX = event.getX();
            mY = event.getY();

            if (mPulseAnimatorSet != null && mPulseAnimatorSet.isRunning()) {
                mPulseAnimatorSet.cancel();
            }
            mPaint.setColor(Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256)));
            mPulseAnimatorSet.start();
        }

        return super.onTouchEvent(event);
    }
}
