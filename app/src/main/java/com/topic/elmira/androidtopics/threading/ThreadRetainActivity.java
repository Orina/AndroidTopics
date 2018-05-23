package com.topic.elmira.androidtopics.threading;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.topic.elmira.androidtopics.R;

public class ThreadRetainActivity extends AppCompatActivity {

    private static MyThread t;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_retain);

        Object retainedObject = getLastCustomNonConfigurationInstance();
        if (retainedObject != null) {
            t = (MyThread) retainedObject;
            t.attach(this);
        }
        else{
            t = new MyThread(this);
        }
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return t;
    }

    private void setText(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(text);
            }
        });
    }

    private static class MyThread extends Thread {
        private ThreadRetainActivity mActivity;

        public MyThread(ThreadRetainActivity mActivity) {
            this.mActivity = mActivity;
        }

        private void attach(ThreadRetainActivity activity) {
            mActivity = activity;
        }

        @Override
        public void run() {
            final String textFromNetwork = getTextFromNetwork();
            mActivity.setText(textFromNetwork);
        }

        private String getTextFromNetwork() {
            SystemClock.sleep(5000);
            return "Text from network";
        }
    }
}
