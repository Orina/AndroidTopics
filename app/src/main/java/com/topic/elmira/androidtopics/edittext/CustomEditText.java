package com.topic.elmira.androidtopics.edittext;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import com.topic.elmira.androidtopics.R;

/**
 * Created by Elmira Andreeva on 6/8/18.
 */

public class CustomEditText extends AppCompatEditText {

    private Drawable applyButton;

    public CustomEditText(Context context) {
        super(context);
        init(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        applyButton = context.getDrawable(R.drawable.apply_button);

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                showApplyButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void showApplyButton(){
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, applyButton, null);
    }

    private void hideApplyButton(){
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
    }
}
