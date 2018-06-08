package com.topic.elmira.androidtopics.rating;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.topic.elmira.androidtopics.R;

/**
 * Created by Elmira Andreeva on 6/7/18.
 */

public class CustomRatingBarXmlRating extends LinearLayout {

    private TextView textView;
    private RatingBar ratingBar;

    public CustomRatingBarXmlRating(Context context) {
        super(context);
        init(context);
    }

    public CustomRatingBarXmlRating(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomRatingBarXmlRating(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public CustomRatingBarXmlRating(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        textView = new TextView(context);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setText("Hello, world!");
        addView(textView);

        LayoutInflater.from(context).inflate(R.layout.custom_rating_bar_only, this);
        //addView(ratingBar);
    }
}
