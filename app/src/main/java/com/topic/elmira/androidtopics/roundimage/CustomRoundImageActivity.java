package com.topic.elmira.androidtopics.roundimage;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.topic.elmira.androidtopics.R;

public class CustomRoundImageActivity extends AppCompatActivity {

    private CustomRoundImage roundImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_round_image);

        roundImage = findViewById(R.id.roundImage);
        roundImage.setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.image3));
    }
}
