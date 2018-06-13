package com.topic.elmira.androidtopics.arcanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.topic.elmira.androidtopics.R;

/**
 * Created by Elmira Andreeva on 6/10/18.
 */

public class ArcAnimationView extends View {

    private RectF mRect;
    private Paint mPaint;
    private Paint mBgrPaint;
    private Paint mTextPaint;

    private int mStartAngle = 270;
    private int mEndAngle;
    private float mStrokeWidth;

    private ObjectAnimator animator;
    float padding = 12;
    float mDensity;

    private String mPrice;
    private boolean isDrawHook = false;

    private Path mHookPath;
    private Paint mHookPaint;

    public ArcAnimationView(Context context) {
        super(context);
        init(context);
    }

    public ArcAnimationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ArcAnimationView, 0, 0);
        try {
            mBgrPaint.setColor(typedArray.getColor(R.styleable.ArcAnimationView_backgroundColor, mBgrPaint.getColor()));
            mPaint.setColor(typedArray.getColor(R.styleable.ArcAnimationView_fillColor, mPaint.getColor()));
            mStartAngle = typedArray.getInt(R.styleable.ArcAnimationView_startAngle, mStartAngle);
            mStrokeWidth = typedArray.getDimension(R.styleable.ArcAnimationView_strokeWidth, mStrokeWidth);
        } finally {
            typedArray.recycle();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRect = new RectF(padding, padding, w - padding, h - padding);
    }

    private void init(Context context) {
        mDensity = context.getResources().getDisplayMetrics().density;
        mStrokeWidth = 5 * mDensity;

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(context.getColor(R.color.colorPrimary));
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);

        mBgrPaint = new Paint(mPaint);
        mBgrPaint.setColor(Color.LTGRAY);

        setupAnimation();
        padding = padding * mDensity;

        mTextPaint = new Paint();
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(52);
        mTextPaint.setAntiAlias(true);

        mPrice = "$153.80";

        mHookPath = new Path();
        mHookPaint = new Paint();
        mHookPaint.setAntiAlias(true);
        mHookPaint.setStrokeCap(Paint.Cap.ROUND);
        mHookPaint.setStrokeJoin(Paint.Join.ROUND);
        mHookPaint.setStrokeWidth(4 * mDensity);
        mHookPaint.setStyle(Paint.Style.STROKE);
        mHookPaint.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(mRect, mEndAngle, 360, false, mBgrPaint);
        canvas.drawArc(mRect, mStartAngle, mEndAngle, false, mPaint);
        canvas.drawText(mPrice, getWidth() / 2, getHeight() / 2 - (mTextPaint.descent() + mTextPaint.ascent()) / 2, mTextPaint);

        if (isDrawHook) {
            drawHook(canvas);
        }
    }

    public void setAngle(float value) {
        //mStartAngle = mEndAngle;
        mEndAngle = (int) value;
        invalidate();
    }

    private void setupAnimation() {
        animator = ObjectAnimator.ofFloat(this, "angle", 0, 360);
        animator.setDuration(4000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isDrawHook = true;
                invalidate();
            }
        });
        animator.start();
    }

    private void drawHook(Canvas canvas) {
        //float offset = 60* mDensity;
        float offset = getWidth() / 8;
        mHookPath.moveTo(getWidth() - offset, getHeight() / 4);
        mHookPath.lineTo(getWidth() - offset * 3 / 4, getHeight() / 4 + offset);
        mHookPath.lineTo(getWidth() - offset / 2, getHeight() / 4);
        canvas.drawPath(mHookPath, mHookPaint);
    }


    public void reDraw() {
        if (animator.isRunning()) {
            animator.cancel();
        }

        isDrawHook = false;
        animator.start();
    }

    public void setPrice(String price){
        mPrice = price;
        reDraw();
    }
}
