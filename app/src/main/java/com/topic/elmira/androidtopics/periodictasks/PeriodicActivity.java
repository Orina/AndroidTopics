package com.topic.elmira.androidtopics.periodictasks;

import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Process;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.topic.elmira.androidtopics.R;

/**
 * Created by Elmira Andreeva on 5/27/18.
 */

public class PeriodicActivity extends AppCompatActivity {

    public static final String LOG_TAG = "PeriodicActivity";

    private HandlerThread handlerThread;
    private PeriodicHandlerLooperScheduler mHandlerScheduler;
    private PeriodicHandlerLooperScheduler.TaskInfo taskInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periodic);

        handlerThread = new HandlerThread("Periodic Handler thread", Process.THREAD_PRIORITY_BACKGROUND);
        handlerThread.start();

        mHandlerScheduler = new PeriodicHandlerLooperScheduler(handlerThread.getLooper());

        startHandlerLooperPeriodicTask();
        startAlarmService();

        startScheduledThreadExecutor();
    }

    public void startHandlerLooperPeriodicTask() {
        PeriodicHandlerLooperScheduler.Task task = new PeriodicHandlerLooperScheduler.Task() {
            @Override
            public void doTask() {
                Log.d(LOG_TAG, "start doTask()");
                SystemClock.sleep(2000);
                Log.d(LOG_TAG, "stop doTask()");
            }
        };
        taskInfo = new PeriodicHandlerLooperScheduler.TaskInfo(4000, task);
        mHandlerScheduler.scheduleTask(taskInfo);
    }

    public void startAlarmService() {
        AlarmScheduler.startTask(this);
    }

    public void startScheduledThreadExecutor() {
        PeriodicThreadPoolScheduler threadPoolScheduler = PeriodicThreadPoolScheduler.getInstance();
        threadPoolScheduler.schedule(new Runnable() {
            @Override
            public void run() {
                Log.d(LOG_TAG, "ScheduledThreadExecutor start");
                SystemClock.sleep(4000);
                Log.d(LOG_TAG, "ScheduledThreadExecutor stop");
            }
        }, 20);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mHandlerScheduler.stopTask(taskInfo);
        handlerThread.quit();

        AlarmScheduler.stopTask(this);

        PeriodicThreadPoolScheduler.getInstance().stop();
    }
}
