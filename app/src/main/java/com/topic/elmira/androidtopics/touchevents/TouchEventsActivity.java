package com.topic.elmira.androidtopics.touchevents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.topic.elmira.androidtopics.R;

public class TouchEventsActivity extends AppCompatActivity {

    private TouchLineView touchLineView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_events);

        touchLineView = findViewById(R.id.touchView);
    }

    public void clearTouchView(View view){
        touchLineView.clearAll();
    }
}
