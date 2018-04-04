package com.topic.elmira.androidtopics.circledrawableslist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.topic.elmira.androidtopics.R;

import java.util.ArrayList;
import java.util.List;

public class CircleDrawablesListActivity extends AppCompatActivity {

    private CircleDrawableListView circleListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_drawables_list);

        circleListView = findViewById(R.id.circle_drawable_list);
        setupCircleView();
    }

    private void setupCircleView(){
        List<Bitmap> list = new ArrayList<>();
        Bitmap image1 = BitmapFactory.decodeResource(getResources(), R.drawable.image1);
        Bitmap image2 = BitmapFactory.decodeResource(getResources(), R.drawable.image2);
        Bitmap image3 = BitmapFactory.decodeResource(getResources(), R.drawable.image3);

        list.add(image1);
        list.add(image2);
        list.add(image3);

        circleListView.setDrawables(list);
    }
}
