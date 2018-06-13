package com.topic.elmira.androidtopics.progressbar;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.topic.elmira.androidtopics.R;

/**
 * Created by Elmira Andreeva on 6/12/18.
 */

public class ProgressBarWithTextView extends LinearLayout {

    private TextView mTextView;
    private ProgressBar mProgressBar;

    private int mProgress;

    public ProgressBarWithTextView(Context context) {
        super(context);
        init(context);
    }

    public ProgressBarWithTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ProgressBarWithTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ProgressBarWithTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.view_progress_bar_with_text, this);

        mTextView = findViewById(R.id.textView);
        mProgressBar = findViewById(R.id.progressBar);

        startAnimation();
    }

    public void setProgress(float value) {
        this.mProgress = (int) value;

        mTextView.setText("" + mProgress + "%");
        mTextView.invalidate();

        mProgressBar.setProgress(mProgress);
        mProgressBar.invalidate();
    }

    private void startAnimation(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "progress", 0, 100);
        animator.setInterpolator(new LinearInterpolator());
        animator.setDuration(2000);
        animator.start();
    }
}
