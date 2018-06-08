package com.topic.elmira.androidtopics.rating;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.topic.elmira.androidtopics.R;

/**
 * Created by Elmira Andreeva on 6/7/18.
 */

public class CustomRatingBarXmlAll extends LinearLayout {

    private RatingBar ratingBar;
    private TextView textView;
    String[] textValues;

    public CustomRatingBarXmlAll(Context context) {
        super(context);
        init(context);
    }

    public CustomRatingBarXmlAll(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomRatingBarXmlAll(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public CustomRatingBarXmlAll(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        inflate(context, R.layout.custom_rating_bar, this);

        textValues = context.getResources().getStringArray(R.array.rating_values);

        ratingBar = findViewById(R.id.ratingBar);
        textView = findViewById(R.id.ratingText);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                setRating((int)rating);
            }
        });
    }

    public void setRating(int value){
        int id = Math.max(0, value - 1) % textValues.length;
        textView.setText(textValues[id]);
        textView.invalidate();
    }
}
