package com.topic.elmira.androidtopics.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.topic.elmira.androidtopics.R;

public class AnimationActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        textView = findViewById(R.id.text_view);
        button = findViewById(R.id.animateBtn);

        final Animator btnAnimator = AnimatorInflater.loadAnimator(this, R.animator.slide_down_anim);
        btnAnimator.setTarget(button);
        btnAnimator.start();
        btnAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ObjectAnimator anim = ObjectAnimator.ofFloat(textView, "ScaleY", 0, 1);
                anim.setInterpolator(new BounceInterpolator());
                anim.start();
            }
        });

    }

    public void startAnimation(View view){
        textView.setAlpha(0);
        textView.setScaleX(0);
        textView.setScaleY(0);

        textView.animate()
                .alpha(1)
                .scaleY(1)
                .scaleX(1)
                .rotation(45f)
                .translationY(textView.getWidth())
                .translationZ(24f)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(1000)
                .start();

        /*ValueAnimator animator = ValueAnimator.ofFloat(1, 0.5f, 1);
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
        */

    }
}
