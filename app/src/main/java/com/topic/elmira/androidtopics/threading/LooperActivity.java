package com.topic.elmira.androidtopics.threading;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.topic.elmira.androidtopics.R;

public class LooperActivity extends AppCompatActivity {

    private LooperThread mLooperThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looper);

        mLooperThread = new LooperThread();
        mLooperThread.start();
    }

    private class LooperThread extends Thread{
        public Handler mHandler;

        @Override
        public void run() {
            Looper.prepare();
            mHandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 0){
                        doLongOperation();
                    }
                }
            };
            Looper.loop();
        }
    }


    private void doLongOperation(){

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLooperThread.mHandler.getLooper().quit();
    }

    public void onClick(View view){
        if (mLooperThread.mHandler!=null){
            Message msg = mLooperThread.mHandler.obtainMessage(0);
            mLooperThread.mHandler.sendMessage(msg);
        }
    }
}
