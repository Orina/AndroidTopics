package com.topic.elmira.androidtopics.edittext;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.topic.elmira.androidtopics.R;

/**
 * Created by Elmira Andreeva on 6/8/18.
 */

public class CustomEditText extends AppCompatEditText {

    int width;
    private Drawable applyButton;
    private boolean isDisplayApplyButton = false;

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

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
    }

    private void init(Context context) {
        applyButton = context.getDrawable(R.drawable.apply_button);

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isDisplayApplyButton) {
                    showApplyButton();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void showApplyButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, applyButton, null);
        isDisplayApplyButton = true;
    }

    private void hideApplyButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, null, null);
        isDisplayApplyButton = false;
    }

    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!performClick()) return false;

        int startApplyButtonWidth = getWidth() - getPaddingRight() - applyButton.getIntrinsicWidth();
        if (event.getX() > startApplyButtonWidth) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                return true;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                hideApplyButton();
                getText().clear();
                return true;
            } else return super.onTouchEvent(event);
        } else return super.onTouchEvent(event);
    }
}
