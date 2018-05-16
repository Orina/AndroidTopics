package com.topic.elmira.androidtopics.touchevents;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.topic.elmira.androidtopics.R;

/**
 * Created by Elmira on 3/8/2018.
 */

public class ShapeSelectorView extends View {

    private static final String LOG_TAG = "ShapeSelectorView";

    private int shapeColor;
    private boolean showName;

    private int defaultShapeWidth = 200;
    private int defaultShapeHeight = 200;
    private Paint shapePaint;

    private int textXOffset = 0;
    private int textYOffset = 100;
    private int textPadding = 10;

    private int currentShapeIdx = 0;
    private String[] shapeValues = new String[]{"Square", "Circle", "Triangle"};
    private Path trianglePath;

    public ShapeSelectorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setupAttributes(attrs);
        setupPaint();
        trianglePath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(LOG_TAG, "onDraw()");
        super.onDraw(canvas);

        int shapeWidth = getMeasuredWidth();
        int shapeHeight = getMeasuredHeight();

        //Square
        if (currentShapeIdx==0){
            canvas.drawRect(0, 0, shapeWidth, shapeHeight, shapePaint);
            textXOffset = 0;
        }
        //Circle
        else if (currentShapeIdx==1){
            canvas.drawCircle(shapeWidth/2, shapeHeight/2, shapeWidth/2, shapePaint);
            textXOffset = 12;
        }
        //triangle
        else if (currentShapeIdx==2){
            canvas.drawPath(getTrianglePath(shapeWidth, shapeHeight), shapePaint);
            textXOffset = 0;
        }

        //canvas.drawRect(0, 0, defaultShapeWidth, defaultShapeHeight, shapePaint);
        if (showName) {
            canvas.drawText(shapeValues[currentShapeIdx], textXOffset, shapeHeight + textYOffset, shapePaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(LOG_TAG, "onMeasure()");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int contentWidth = defaultShapeWidth;

        int minWidth = getPaddingStart() + contentWidth + getPaddingEnd();
        int w = resolveSizeAndState(minWidth, widthMeasureSpec, 0);

        int minHeight = getPaddingTop() + defaultShapeHeight + getPaddingBottom();
        if (showName) {
            minHeight += textYOffset + textPadding;
        }
        int h = resolveSizeAndState(minHeight, heightMeasureSpec, 0);

        setMeasuredDimension(w, h);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(LOG_TAG, "onTouchEvent() action: " + event.getAction());
        boolean result = super.onTouchEvent(event);

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            currentShapeIdx = (currentShapeIdx + 1) % shapeValues.length;
            invalidate();
            return true;
        }

        return result;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    private Path getTrianglePath(int shapeWidth, int shapeHeight){
        trianglePath.moveTo(0, shapeHeight);
        trianglePath.lineTo(shapeWidth, shapeHeight);
        trianglePath.lineTo(shapeWidth /2, 0);
        return trianglePath;
    }

    private void setupAttributes(AttributeSet attrs) {
        TypedArray a = getContext().getTheme()
                .obtainStyledAttributes(attrs, R.styleable.ShapeSelectorView, 0, 0);

        try {
            shapeColor = a.getColor(R.styleable.ShapeSelectorView_shapeColor, Color.BLACK);
            showName = a.getBoolean(R.styleable.ShapeSelectorView_showShapeName, true);
        } finally {
            a.recycle();
        }
    }

    private void setupPaint() {
        shapePaint = new Paint();
        shapePaint.setColor(shapeColor);
        shapePaint.setStyle(Paint.Style.FILL);
        shapePaint.setTextSize(60);
    }

    public int getShapeColor() {
        return shapeColor;
    }

    public void setShapeColor(int shapeColor) {
        this.shapeColor = shapeColor;
        invalidate();
        requestLayout();
    }

    public boolean isShowName() {
        return showName;
    }

    public void setShowName(boolean showName) {
        this.showName = showName;
        invalidate();
        requestLayout();
    }
}
