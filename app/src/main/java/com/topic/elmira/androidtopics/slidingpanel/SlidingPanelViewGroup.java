package com.topic.elmira.androidtopics.slidingpanel;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.topic.elmira.androidtopics.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elmira on 3/17/2018.
 */

public class SlidingPanelViewGroup extends FrameLayout {

    public static final String LOG_TAG = "SlidingPanelVG";
    int mItemsCount = 0;

    int totalWidth = 600;
    int height = 200;
    int itemWidth = 0;

    private List<TextView> mItemsTextView;
    private String[] mItemsTitle;

    private int mItemsColor;
    private int mItemsTextColor;
    private int mItemsSelectedColor;
    private int mSliderColor;

    private TextView mSliderView;
    private GestureDetector mGestureDetector;

    private int mCurSliderPos = 0;

    public SlidingPanelViewGroup(Context context) {
        this(context, null);
    }

    public SlidingPanelViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray arr = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SlidingPanelViewGroup, 0, 0);
        try {
            mItemsCount = arr.getInteger(R.styleable.SlidingPanelViewGroup_itemsCount, 1);

            mItemsColor = arr.getColor(R.styleable.SlidingPanelViewGroup_itemsColor, getResources().getColor(R.color.colorAccent));
            mSliderColor = arr.getColor(R.styleable.SlidingPanelViewGroup_sliderColor, getResources().getColor(R.color.colorPrimaryDark));

            mItemsTextColor = arr.getColor(R.styleable.SlidingPanelViewGroup_itemsTextColor, getResources().getColor(R.color.colorPrimaryDark));
            mItemsSelectedColor = arr.getColor(R.styleable.SlidingPanelViewGroup_itemsSelectedTextColor, getResources().getColor(R.color.colorPrimary));
        } finally {
            arr.recycle();
        }

        mItemsTextView = new ArrayList<>();
        for (int i = 0; i < mItemsCount; i++) {
            TextView item = new TextView(this.getContext());
            //item.setTextAlignment(TEXT_ALIGNMEN);
            final int pos = i;
            item.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(LOG_TAG, "onClick() text view");
                    moveSliderToPosition(pos);
                }
            });
            item.setText("i: " + i);
            item.setBackgroundColor(mItemsColor);
            addView(item, i);
            mItemsTextView.add(item);
        }


        mCurSliderPos = 0;
        mSliderView = new TextView(this.getContext());
        mSliderView.setText("Bar");
        mSliderView.setBackgroundColor(mSliderColor);
        addView(mSliderView, mItemsCount);

        final SlidingGestureDetectorListener gestureListener = new SlidingGestureDetectorListener();
        mGestureDetector = new GestureDetector(mSliderView.getContext(), gestureListener);

        mSliderView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean result = mGestureDetector.onTouchEvent(event);

                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Log.d(LOG_TAG, "MotionEvent.ACTION_UP");
                    if (mCurSliderPos != gestureListener.curPos) {
                        View view = getChildAt(gestureListener.curPos);
                        mSliderView.setLeft(view.getLeft());
                        mSliderView.setRight(view.getRight());
                        mCurSliderPos = gestureListener.curPos;
                        invalidate();
                    }
                    return true;
                }

                return result;
            }
        });

    }

    @Override
    protected void onAttachedToWindow() {
        Log.d(LOG_TAG, "onAttachedToWindow()");
        super.onAttachedToWindow();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(LOG_TAG, "onMeasure()");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY) {
            totalWidth = MeasureSpec.getSize(widthMeasureSpec);
            totalWidth -= getLeftPaddingOffset() + getRightPaddingOffset();
        } else {
            totalWidth = resolveSize(
                    totalWidth + getLeftPaddingOffset() + getRightPaddingOffset(),
                    widthMeasureSpec);
        }

        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) {
            height = MeasureSpec.getSize(heightMeasureSpec);
        } else {
            height = resolveSize(
                    height + getTopPaddingOffset() + getBottomPaddingOffset(),
                    heightMeasureSpec);
        }

        itemWidth = (totalWidth - getLeftPaddingOffset() - getRightPaddingOffset()) / mItemsCount;
        setMeasuredDimension(totalWidth, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.d(LOG_TAG, "onLayout()");
        super.onLayout(changed, left, top, right, bottom);

        int x = left - getLeftPaddingOffset();
        for (int i = 0; i < mItemsCount; i++) {
            TextView textView = (TextView) getChildAt(i);
            textView.setLeft(x);
            int dx = x + itemWidth;
            textView.setRight(dx);

            if (i == mCurSliderPos) {
                mSliderView.setLeft(x);
                mSliderView.setRight(dx);
            }

            x = dx;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    private void moveSliderToPosition(int pos) {
        Log.d(LOG_TAG, "moveSliderToPosition() pos: " + pos + ", mCurSliderPos: " + mCurSliderPos);
        if (pos < 0 || pos >= mItemsCount) return;
        if (mCurSliderPos == pos) return;

        mSliderView.setText(mItemsTextView.get(pos).getText());
        mCurSliderPos = pos;

        requestLayout();
    }

    private int getItemPositionByCoordinate(float x) {
        x -= getLeft();
        int px = (int) (x / itemWidth);
        return ((x - px * itemWidth) >= 0.5f) ? px : Math.max(0, px);
    }

    public interface OnSlidingItemClickListener {
        void onItemClick(int pos);
    }

    private class SlidingGestureDetectorListener extends GestureDetector.SimpleOnGestureListener {
        public static final String LOG_TAG = "SlidingGestureListener";
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        public int curPos = 0;

        @Override
        public boolean onDown(MotionEvent e) {
            Log.d(LOG_TAG, "onDown()");
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d(LOG_TAG, "onScroll()");
             curPos = getItemPositionByCoordinate(mSliderView.getX());
            Log.d(LOG_TAG, "detect position: " + curPos);

            int dx = (int) (e2.getX() - e1.getX());
            mSliderView.setLeft(mSliderView.getLeft() + dx);
            mSliderView.setRight(mSliderView.getRight() + dx);
            invalidate();

            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d(LOG_TAG, "onFling()");
            boolean result = false;
            //if (velocityX > SWIPE_VELOCITY_THRESHOLD){
            float diffX = e2.getX() - e1.getX();
            curPos = getItemPositionByCoordinate(e2.getX());
            Log.d(LOG_TAG, "detect position: " + curPos);
            result = true;
            //}
            return result;
        }


    }
}
