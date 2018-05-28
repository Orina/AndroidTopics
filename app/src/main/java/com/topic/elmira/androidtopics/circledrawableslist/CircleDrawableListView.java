package com.topic.elmira.androidtopics.circledrawableslist;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.topic.elmira.androidtopics.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elmira on 3/15/2018.
 */

public class CircleDrawableListView extends View {

    public static final String LOG_TAG = "CircleDrawableListView";

    int mMaxCount = 0;

    int mCircleSize = 200;
    int mHorizontalPadding = 10;
    int radius = 0;

    List<Drawable> mDrawables;
    Paint borderPaint;
    Paint textPaint;

    public CircleDrawableListView(Context context) {
        super(context);
    }

    public CircleDrawableListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CircleDrawableListView, 0, 0);

        try {
            mMaxCount = typedArray.getInteger(R.styleable.CircleDrawableListView_maxCount, -1);
            mHorizontalPadding = (int) typedArray.getDimension(R.styleable.CircleDrawableListView_circlePadding, 10);

        } finally {
            typedArray.recycle();
        }

        mDrawables = new ArrayList<>();

        borderPaint = new Paint();
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(10);
        borderPaint.setColor(ContextCompat.getColor(context, R.color.colorAccent));
        borderPaint.setAntiAlias(true);

        textPaint = new TextPaint();
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(ContextCompat.getColor(context, R.color.colorAccent));
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(50);
        textPaint.setLinearText(true);
        textPaint.setTextAlign(Paint.Align.RIGHT);
    }

    public void setDrawables(List<Bitmap> list) {
        Log.d(LOG_TAG, "setDrawables()");
        if (list == null || list.size() == 0) return;
        mDrawables = new ArrayList<>();

        for (int i = 0; i < mMaxCount && i < list.size(); i++) {
            RoundedBitmapDrawable d =
                    RoundedBitmapDrawableFactory.create(getResources(), list.get(i));

            d.setCircular(true);
            d.setAntiAlias(true);

            mDrawables.add(d);
        }

        requestLayout();
    }

    public void addDrawable(Drawable drawable) {
        Log.d(LOG_TAG, "addDrawable()");
        if (mMaxCount > 0 && mDrawables.size() >= mMaxCount) {
            Log.d(LOG_TAG, "Can't add more drawables, we are full");
            return;
        }
        mDrawables.add(drawable);
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(LOG_TAG, "onMeasure()");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = 0;
        int count = mDrawables.size();

        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) {
            mCircleSize = MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop() - getPaddingBottom();
        }

        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY) {
            width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingStart() - getPaddingEnd();
        } else {
            int radius = mCircleSize / 2;
            width = mCircleSize + getPaddingStart() + getPaddingEnd();
            width += count > 1 ? (count - 1) * radius : 0;
            width += count > 1 ? (count - 1) * mHorizontalPadding : 0;
        }

        setMeasuredDimension(resolveSize(width, widthMeasureSpec),
                resolveSize(mCircleSize + getPaddingTop() + getPaddingBottom(), heightMeasureSpec));

        refreshDrawableBounds();
    }

    private void refreshDrawableBounds() {
        Log.d(LOG_TAG, "refreshDrawableBounds()");
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();

        int x = paddingLeft;
        int y = getPaddingTop();

        int circleWidth = mCircleSize;
        int radius = mCircleSize / 2;

        for (Drawable drawable : mDrawables) {
            drawable.setBounds(x, y, x + circleWidth, y + circleWidth);
            x += radius + mHorizontalPadding;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(LOG_TAG, "onDraw()");
        super.onDraw(canvas);

        if (mDrawables.isEmpty()) return;

        Rect rect = null;
        for (Drawable drawable : mDrawables) {
            drawable.draw(canvas);
            rect = drawable.getBounds();
            canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width() / 2, borderPaint);
        }

        canvas.drawText("+1", rect.right - getTopPaddingOffset(), rect.top, textPaint);
    }
}
