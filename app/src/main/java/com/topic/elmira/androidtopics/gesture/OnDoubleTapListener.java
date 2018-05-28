package com.topic.elmira.androidtopics.gesture;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Elmira Andreeva on 5/23/18.
 */

public class OnDoubleTapListener implements View.OnTouchListener {

    private GestureDetector gestureDetector;

    public OnDoubleTapListener(Context context) {
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            OnDoubleTapListener.this.onDoubleTap();
            return super.onDoubleTapEvent(e);
        }
    }

    public void onDoubleTap(){

    }
}
