package com.topic.elmira.androidtopics.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.topic.elmira.androidtopics.R;

public class AnimationActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        textView = findViewById(R.id.text_view);
    }

    public void startAnimation(View view){
        ValueAnimator animator = ValueAnimator.ofFloat(1, 0.5f, 1);
        animator.setDuration(2000);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float)animation.getAnimatedValue();
                textView.setAlpha(value);
            }
        });
        animator.start();

        Animator fadeAnim = AnimatorInflater.loadAnimator(view.getContext(), R.animator.anim_fade_out);
        fadeAnim.setTarget(view);
        fadeAnim.start();
    }
}
