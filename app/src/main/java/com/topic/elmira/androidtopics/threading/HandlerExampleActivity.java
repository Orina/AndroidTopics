package com.topic.elmira.androidtopics.threading;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.topic.elmira.androidtopics.R;

import java.util.Random;

public class HandlerExampleActivity extends AppCompatActivity {

    public static final int SHOW_PROGRESS_BAR = 1;
    public static final int HIDE_PROGRESS_BAR = 0;

    private BackgroundThread backgroundThread;
    private static Handler mUiHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SHOW_PROGRESS_BAR:
                    break;

                case HIDE_PROGRESS_BAR:
                    break;
            }
        }
    };
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_example);

        backgroundThread = new BackgroundThread();
        backgroundThread.start();
    }

    public void onClick(View view){
        backgroundThread.doWork();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        backgroundThread.exit();
    }

    private class BackgroundThread extends Thread {

        private Handler mHandler;

        Random rand = new Random();

        @Override
        public void run() {
            Looper.prepare();
            mHandler = new Handler();
            Looper.loop();
        }

        public void doWork(){
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Message uiMsg = mUiHandler.obtainMessage(HandlerExampleActivity.SHOW_PROGRESS_BAR);
                    mUiHandler.sendMessage(uiMsg);

                    SystemClock.sleep(rand.nextInt(5000));

                    uiMsg = mUiHandler.obtainMessage(HandlerExampleActivity.HIDE_PROGRESS_BAR);
                    mUiHandler.sendMessage(uiMsg);
                }
            });
        }

        public void exit(){
            mHandler.getLooper().quit();
        }
    }
}
