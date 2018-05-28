package com.topic.elmira.androidtopics.sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.topic.elmira.androidtopics.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SharedPreferencesActivity extends AppCompatActivity {

    @BindView(R.id.key_text_view)
    EditText editText;

    private SharedPreferencesThread mSharedHandlerThread;

    private Handler mUiHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                String value = (String) msg.obj;
                editText.setText(value);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        ButterKnife.bind(this);

        mSharedHandlerThread = new SharedPreferencesThread();
        mSharedHandlerThread.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSharedHandlerThread.read();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSharedHandlerThread.quit();
    }

    public void onSave(View view){
        mSharedHandlerThread.write(editText.getText().toString());
    }

    private class SharedPreferencesThread extends HandlerThread{

        public static final String KEY= "somekey";
        private SharedPreferences mPrefs;
        private Handler mHandler;

        public static final int READ = 1;
        public static final int WRITE = 0;

        public SharedPreferencesThread() {
            super("SharedPreferencesThread", Process.THREAD_PRIORITY_BACKGROUND);
            mPrefs = getSharedPreferences("LocalPrefs", MODE_PRIVATE);
        }

        @Override
        protected void onLooperPrepared() {
            super.onLooperPrepared();
            mHandler = new Handler(getLooper()){
                @Override
                public void handleMessage(Message msg) {
                    switch (msg.what){
                        case READ:
                            mUiHandler.sendMessage(mUiHandler.obtainMessage(0, mPrefs.getString(KEY, "empty")));
                            break;
                        case WRITE:
                            SharedPreferences.Editor editor = mPrefs.edit();
                            editor.putString(KEY, (String)msg.obj);
                            editor.commit();
                            break;
                    }
                }
            };
        }

        public void read(){
            mHandler.sendEmptyMessage(READ);
        }

        public void write(String value){
           mHandler.sendMessage(mHandler.obtainMessage(WRITE, value));
        }
    }
}
