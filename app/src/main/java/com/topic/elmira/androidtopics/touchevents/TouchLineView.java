package com.topic.elmira.androidtopics.touchevents;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Elmira Andreeva on 5/15/18.
 */

public class TouchLineView extends View {
    private static final String LOG_TAG = "TouchLineView";

    private Path path = new Path();
    private Paint paint;

    public TouchLineView(Context context) {
        super(context);
        setupPaint();
    }

    public TouchLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setupPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(LOG_TAG, "onDraw()");
        canvas.drawPath(path, paint);
    }

    private void setupPaint() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //if (!performClick()) return false;
        Log.d(LOG_TAG, "onTouchEvent: " + event.getAction());

        float pointX = event.getX();
        float pointY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(pointX, pointY);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(pointX, pointY);
                break;

            default:
                return false;
        }

        invalidate();
        return true;
    }

    public void clearAll() {
        path.reset();
        invalidate();
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }
}
