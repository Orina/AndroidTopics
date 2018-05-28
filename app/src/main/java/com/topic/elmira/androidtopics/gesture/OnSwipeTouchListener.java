package com.topic.elmira.androidtopics.gesture;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Elmira Andreeva on 5/23/18.
 */

public class OnSwipeTouchListener implements View.OnTouchListener {

    private GestureDetector gestureDetector;

    public OnSwipeTouchListener(Context context) {
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        public static final int SWIPE_THRESHOLD = 10;
        public static final int SWIPE_VELOCITY = 10;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float diffX = e2.getX() - e1.getX();
            float diffY = e2.getY() - e1.getY();

            if (Math.abs(diffX) > Math.abs(diffY)){
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) >SWIPE_VELOCITY) {
                    if (diffX > 0) {
                        onSwipeRight();
                        return true;
                    }
                    else {
                        onSwipeLeft();
                        return true;
                    }
                }
            }
            else {
                if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY){
                    if (diffY > 0) {
                        onSwipeDown();
                        return true;
                    }
                    else {
                        onSwipeUp();
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public void onSwipeUp() {

    }

    public void onSwipeDown() {

    }

    public void onSwipeLeft() {

    }

    public void onSwipeRight() {

    }
}
