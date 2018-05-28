package com.topic.elmira.androidtopics.executors;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.topic.elmira.androidtopics.R;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorsActivity extends AppCompatActivity {

    ExecutorService executorService = Executors.newSingleThreadExecutor();
    public static final String LOG_TAG = "ExecutorsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_executors);
    }

    public void executeRunnable() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Log.d(LOG_TAG, "run Runnable on executor service");
            }
        });
    }

    public void executeCallable() {
        Future<Bitmap> future = executorService.submit(new Callable<Bitmap>() {
            @Override
            public Bitmap call() throws Exception {
                return loadBitmap();
            }
        });
        try {
            Bitmap bitmap = future.get();
        } catch (Exception ex) {
            Log.e(LOG_TAG, ex.getMessage(), ex);
        }
    }


    private Bitmap loadBitmap() {
        return null;
    }
}
