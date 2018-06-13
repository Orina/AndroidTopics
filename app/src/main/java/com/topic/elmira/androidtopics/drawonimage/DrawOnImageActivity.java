package com.topic.elmira.androidtopics.drawonimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.topic.elmira.androidtopics.R;

public class DrawOnImageActivity extends AppCompatActivity {

    private ImageView imageView;
    private Paint mPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_on_image);
        imageView = findViewById(R.id.imageView);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.DKGRAY);
        mPaint.setTextSize(100);
        mPaint.setTextAlign(Paint.Align.CENTER);

        drawOnImageView();
    }

    public void drawOnImageView() {

        Bitmap sourceBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image2);

        Bitmap tmpBitmap = Bitmap.createBitmap(sourceBitmap.getWidth(), sourceBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas tmpCanvas = new Canvas(tmpBitmap);

        tmpCanvas.drawBitmap(sourceBitmap, 0, 0, null);
        tmpCanvas.drawText("Hello, world!", 100, 100, mPaint);

        imageView.setImageDrawable(new BitmapDrawable(getResources(), tmpBitmap));

    }
}
