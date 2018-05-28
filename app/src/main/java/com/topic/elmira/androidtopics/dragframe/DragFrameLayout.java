package com.topic.elmira.androidtopics.dragframe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.topic.elmira.androidtopics.R;

/**
 * Created by Elmira on 3/13/2018.
 */

public class DragFrameLayout extends FrameLayout {

    public static final String LOG_TAG = "CircleDragFrameLayout";

    private CircleDragView mCircleView;

    public DragFrameLayout(@NonNull Context context) {
        this(context, null, 0);
    }

    public DragFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mCircleView = findViewById(R.id.circle_drag_id);
    }

    @Override
    public boolean onDragEvent(DragEvent event) {
        //Log.d(LOG_TAG, "onDragEvent() " + event.toString());
        final int action = event.getAction();

        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:
                Log.d(LOG_TAG, "drag started");
                //Toast.makeText(getContext(), "drag started", Toast.LENGTH_SHORT).show();
                mCircleView.animateOnDragStart();
                //mCircleView.setVisibility(View.INVISIBLE);
                //mCircleView.invalidate();
                return true;
            case DragEvent.ACTION_DRAG_LOCATION:
                Log.d(LOG_TAG, "drag location");
                //Toast.makeText(getContext(), "drag location", Toast.LENGTH_SHORT).show();
                return true;
            case DragEvent.ACTION_DRAG_ENTERED:
                Log.d(LOG_TAG, "drag entered");
                //Toast.makeText(getContext(), "drag entered", Toast.LENGTH_SHORT).show();
                return true;
            case DragEvent.ACTION_DROP:
                Log.d(LOG_TAG, "action drop");
                //Toast.makeText(getContext(), "action drop", Toast.LENGTH_SHORT).show();

                int radius = mCircleView.getWidth()/2;
                mCircleView.setX(event.getX() - radius);
                mCircleView.setY(event.getY() - radius);
                mCircleView.setVisibility(View.VISIBLE);
                mCircleView.animateOnDragEnd();

                return true;

            case DragEvent.ACTION_DRAG_ENDED:
                Log.d(LOG_TAG, "drag ended");
                //Toast.makeText(getContext(), "drag ended", Toast.LENGTH_SHORT).show();
                //mCircleView.setVisibility(View.VISIBLE);
                //mCircleView.changeStartPoints((int) getX(), (int) getY());
                mCircleView.setVisibility(View.VISIBLE);
                return true;
        }
        return super.onDragEvent(event);
    }
}
