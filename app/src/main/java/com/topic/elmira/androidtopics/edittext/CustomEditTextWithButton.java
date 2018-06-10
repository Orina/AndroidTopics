package com.topic.elmira.androidtopics.edittext;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.topic.elmira.androidtopics.R;

/**
 * Created by Elmira on 6/9/2018.
 */

public class CustomEditTextWithButton extends LinearLayout {

    private EditText editText;

    private Button applyButton;
    private boolean isDisplayApplyButton = false;

    public CustomEditTextWithButton(Context context) {
        super(context);
        init(context);
    }

    public CustomEditTextWithButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomEditTextWithButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public CustomEditTextWithButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        inflate(context, R.layout.custom_edit_text_with_button, this);

        editText = findViewById(R.id.editText);
        applyButton = findViewById(R.id.applyButton);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()==0){
                    if (isDisplayApplyButton){
                        hideApplyButton();
                    }
                }
                else if (!isDisplayApplyButton){
                    showApplyButton();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void hideApplyButton(){
        isDisplayApplyButton = false;
        applyButton.animate()
                .alpha(0)
                .setDuration(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        //applyButton.setVisibility(View.INVISIBLE);
                    }
                });
        //applyButton.setVisibility(View.INVISIBLE);
    }

    private void showApplyButton(){
        isDisplayApplyButton = true;
        //applyButton.setVisibility(View.VISIBLE);
        applyButton.animate()
                .alpha(1)
                .setDuration(500)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        //applyButton.setVisibility(View.VISIBLE);
                    }
                });

    }
}
