package com.topic.elmira.androidtopics.arcanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.topic.elmira.androidtopics.R;

public class ArcAnimationActivity extends AppCompatActivity {

    private ArcAnimationView arcAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arc_animation);
        arcAnimationView = findViewById(R.id.arcView);
    }

    public void reDraw(View view){
        arcAnimationView.setPrice("BY 22.00");
    }
}
