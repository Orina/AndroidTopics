package com.topic.elmira.androidtopics.dragframe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import com.topic.elmira.androidtopics.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Elmira on 3/12/2018.
 */

public class CircleDragActivity extends AppCompatActivity {

    @BindView(R.id.circle_drag_id)
    CircleDragView circleDragView;

    private Unbinder unbinder;
    private Random random = new Random();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_drag);
        unbinder = ButterKnife.bind(this);
    }


    public void onChangeText(View view) {
        circleDragView.changeText("" + random.nextInt(100));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
