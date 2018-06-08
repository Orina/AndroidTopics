package com.topic.elmira.androidtopics.rating;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.topic.elmira.androidtopics.R;

/**
 * Created by Elmira Andreeva on 6/7/18.
 */

public class CustomRatingBar extends LinearLayout {

    private RatingBar ratingBar;
    private TextView textView;

    String[] textValues;

    public CustomRatingBar(Context context) {
        super(context);
        init(context);
    }

    public CustomRatingBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setOrientation(LinearLayout.VERTICAL);

        textValues = context.getResources().getStringArray(R.array.rating_values);

        ratingBar = new RatingBar(context);
        ratingBar.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        ratingBar.setMax(5);
        ratingBar.setRating(0);
        ratingBar.setStepSize(1);

        //ratingBar.setBackground(context.getDrawable(R.drawable.star_grey));
        //ratingBar.setProgressDrawable(context.getDrawable(R.drawable.star_pink));

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                changeRating((int) rating);
            }
        });

        textView = new TextView(context);
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER_HORIZONTAL);

        addView(textView);
        addView(ratingBar);
    }

    public void changeRating(int value) {
        int id = Math.max(0, value - 1) % textValues.length;
        textView.setText(textValues[id]);
        textView.invalidate();
    }
}
