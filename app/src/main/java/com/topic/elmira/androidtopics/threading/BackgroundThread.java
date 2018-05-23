package com.topic.elmira.androidtopics.threading;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;

import java.util.Random;

/**
 * Created by Elmira Andreeva on 5/22/18.
 */

public class BackgroundThread extends Thread {

    private Handler mHandler;
    private Handler mUiHandler;

    Random rand = new Random();

    @Override
    public void run() {
        Looper.prepare();
        mHandler = new Handler();
        Looper.loop();

        mUiHandler = new Handler(Looper.getMainLooper());
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
