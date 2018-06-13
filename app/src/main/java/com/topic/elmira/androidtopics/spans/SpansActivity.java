package com.topic.elmira.androidtopics.spans;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.TextAppearanceSpan;
import android.widget.TextView;

import com.topic.elmira.androidtopics.R;

public class SpansActivity extends AppCompatActivity {

    private TextView textView;
    private TextView scrollTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spans);

        textView = findViewById(R.id.textView);
        applySpans();

        scrollTextView = findViewById(R.id.scrollTextView);
        scrollTextView.setMovementMethod(new ScrollingMovementMethod());

        TextView paramTextView = findViewById(R.id.paramTextView);
        paramTextView.setText(getString(R.string.stringWithParam, "Elmira", 10));
    }

    private void applySpans() {
        String txt = "I love you, Alexey!";

        SpannableStringBuilder ssb = new SpannableStringBuilder(txt);

        ssb.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.pink)),
                2,
                txt.lastIndexOf("you"),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ssb.setSpan(new BackgroundColorSpan(ContextCompat.getColor(this, R.color.pink_light)),
                txt.lastIndexOf("A"),
                txt.lastIndexOf("!"),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        ssb.setSpan(new TextAppearanceSpan(this, android.R.style.TextAppearance_Large),
                txt.lastIndexOf("A"),
                txt.lastIndexOf("!"),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ssb.setSpan(new ImageSpan(this, R.drawable.ic_favorite),
                txt.lastIndexOf("!"),
                txt.lastIndexOf("!") + 1,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ssb.append("!");
        textView.setText(ssb);
    }
}
