package com.topic.elmira.androidtopics.dragframe;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.topic.elmira.androidtopics.R;

/**
 * Created by Elmira on 3/12/2018.
 */

public class CircleDragView extends View {
    public static final String LOG_TAG = "CircleDragView";

    private String text = "0";

    private Paint paint;
    private TextPaint textPaint;

    private int startX = 0;
    private int startY = 0;

    public CircleDragView(Context context) {
        this(context, null);
    }

    public CircleDragView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setupCircle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(LOG_TAG, "onMeasure()");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.d(LOG_TAG, "onLayout()");
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.d(LOG_TAG, "onSizeChanged()");
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onAttachedToWindow() {
        Log.d(LOG_TAG, "onAttachedToWindow()");
        super.onAttachedToWindow();
        setOnLongClick();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(LOG_TAG, "onDraw()");
        super.onDraw(canvas);

        int w = getWidth();

        int radius = w / 2;
        canvas.drawCircle(startX + radius, startY + radius, radius, paint);

        float textMiddle = (textPaint.ascent() + textPaint.descent()) / 2f;
        canvas.drawText(text, startX + radius, startY + radius - textMiddle, textPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(LOG_TAG, "onTouchEvent(), " + event.toString());
        return super.onTouchEvent(event);
    }

    private void setOnLongClick() {
        this.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                animateOnDragStart();
                return true;
            }
        });
    }

    public void changeText(String txt) {
        if (txt == null || "".equals(txt)) return;
        this.text = txt;
        invalidate();
    }

    public void startDrag() {
        View shadowView = this;
        shadowView.setScaleX(1.2f);
        shadowView.setScaleY(1.2f);

        DragShadowBuilder builder = new DragShadowBuilder(shadowView);

        ClipData.Item item = new ClipData.Item("item");
        String[] types = new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN};
        ClipData clipData = new ClipData("circle", types, item);

        startDrag(clipData, builder, null, 0);
        setVisibility(View.INVISIBLE);
    }

    public void animateOnDragStart() {

        /*AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(CircleDragView.this, View.SCALE_X, 1f, 1.2f),
                ObjectAnimator.ofFloat(CircleDragView.this, View.SCALE_Y, 1f, 1.2f)
        );
        set.setDuration(1000);
        set.setInterpolator(new LinearInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startDrag();
            }
        });
        set.start(); */

        animate().scaleX(1.2f).scaleY(1.2f).setDuration(1000)
                .setInterpolator(new LinearInterpolator())
                .setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startDrag();
            }
        }).start();
    }

    public void animateOnDragEnd() {
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(this, View.SCALE_X, 1f, 2f, 0.2f, 1f);
        anim1.setInterpolator(new LinearInterpolator());

        ObjectAnimator anim2 = ObjectAnimator.ofFloat(this, View.SCALE_Y, 1f, 2f, 0.2f, 1f);
        anim2.setInterpolator(new LinearInterpolator());

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(anim1, anim2);
        animatorSet.start();
    }

    private void setupCircle() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.colorAccent));
        paint.setStyle(Paint.Style.FILL);

        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(getResources().getColor(R.color.colorPrimaryDark));
        textPaint.setTextSize(100);
        textPaint.setTextAlign(Paint.Align.CENTER);
        //textPaint.setTypeface(Typeface.SANS_SERIF);
    }
}
