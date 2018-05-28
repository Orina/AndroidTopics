package com.topic.elmira.androidtopics.gesture;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.topic.elmira.androidtopics.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GestureActivity extends AppCompatActivity {

    @BindView(R.id.gestureTextView)
    TextView gestureTextView;

    @BindView(R.id.image_view_id)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);
        ButterKnife.bind(this);
        setGestureListener();
    }


    @SuppressLint("ClickableViewAccessibility")
    private void setGestureListener(){

        gestureTextView.setOnTouchListener(new OnDoubleTapListener(this){
            @Override
            public void onDoubleTap() {
                Toast.makeText(GestureActivity.this, "onDoubleTap()", Toast.LENGTH_SHORT).show();
            }
        });

        gestureTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GestureActivity.this, "onClick()", Toast.LENGTH_LONG).show();
            }
        });

        imageView.setOnTouchListener(new OnSwipeTouchListener(this){
            @Override
            public void onSwipeUp() {
                Toast.makeText(GestureActivity.this, "onSwipeUp()", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSwipeDown() {
                Toast.makeText(GestureActivity.this, "onSwipeDown()", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSwipeLeft() {
                Toast.makeText(GestureActivity.this, "onSwipeLeft()", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSwipeRight() {
                Toast.makeText(GestureActivity.this, "onSwipeRight()", Toast.LENGTH_LONG).show();
            }
        });
    }
}
