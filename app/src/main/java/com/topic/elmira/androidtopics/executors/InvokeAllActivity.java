package com.topic.elmira.androidtopics.executors;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.topic.elmira.androidtopics.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class InvokeAllActivity extends AppCompatActivity {

    public static final String LOG_TAG = "InvokeAllActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoke_all);
    }

    public void onButtonClick(View view){
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                List<Callable<String>> tasks = new ArrayList<>();
                tasks.add(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return getFirstDataFromNetwork();
                    }
                });
                tasks.add(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return getSecondDataFromNetwork();
                    }
                });

                ExecutorService executor = Executors.newFixedThreadPool(2);
                try{
                    List<Future<String>> futures = executor.invokeAll(tasks);

                    for (Future<String> future: futures){
                        String res = future.get();
                        Log.d(LOG_TAG, "result: " + res);
                    }
                }
                catch (Throwable ex){
                    Log.e(LOG_TAG, ex.getMessage(), ex);
                }

                executor.shutdown();
            }
        });
    }

    public String getFirstDataFromNetwork(){
        SystemClock.sleep(1000);
        return "abc";
    }

    public String getSecondDataFromNetwork(){
        SystemClock.sleep(2000);
        return "dtm";
    }
}
