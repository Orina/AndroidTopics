package com.topic.elmira.androidtopics.facedetection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.vision.face.Face;
import com.topic.elmira.androidtopics.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FaceDetectionActivity extends AppCompatActivity {

    public static final String LOG_TAG = "FaceDetectionActivity";

    private ExecutorService executorService = null;
    private ImageView imageView;
    private Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_detection);

        imageView = findViewById(R.id.imageView);

        executorService = Executors.newSingleThreadExecutor();
        setupPaint();
    }

    public void onProcess(View view) {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d(LOG_TAG, "start detecting faces...");
                   SparseArray<Face> faces = DetectFacesUtil.getInstance().detectFace(getApplicationContext(), R.drawable.face_detection_2);
                   if (faces == null) {
                       showError();
                       return;
                   }
                   showFaces(faces);
                } catch (Throwable ex) {
                    showError();
                }
            }
        });
    }

    public void showError() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(LOG_TAG, "faces not found");
                new AlertDialog.Builder(getApplicationContext()).setMessage("Could not set up the face detector!").show();
            }
        });
    }

    public void showFaces(final SparseArray<Face> faces) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(LOG_TAG, "found faces...count="+faces.size());

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inMutable=true;

                Bitmap myBitmap = BitmapFactory.decodeResource(
                        getResources(),
                        R.drawable.face_detection_2,
                        options);


                Bitmap tempBitmap = Bitmap.createBitmap(myBitmap.getWidth(), myBitmap.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas tempCanvas = new Canvas(tempBitmap);
                tempCanvas.drawBitmap(myBitmap, 0, 0, null);

                for (int i=0; i< faces.size(); i++){
                    Face face = faces.get(i);
                    float x = face.getPosition().x;
                    float y = face.getPosition().y;
                    tempCanvas.drawRoundRect(new RectF(x, y, x + face.getWidth(), y + face.getHeight()),8, 8, paint);
                }

                imageView.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
            }
        });
    }

    private void setupPaint(){
        paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(ContextCompat.getColor(this, R.color.colorAccent));
        paint.setStyle(Paint.Style.STROKE);

    }
}
