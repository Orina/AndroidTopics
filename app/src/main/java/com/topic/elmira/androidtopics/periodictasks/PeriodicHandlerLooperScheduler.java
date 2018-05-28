package com.topic.elmira.androidtopics.periodictasks;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

/**
 * Created by Elmira Andreeva on 5/27/18.
 */

public class PeriodicHandlerLooperScheduler {

    public static final int PERIODIC_TASK = 22;
    private final PeriodicTaskHandler mHandler;

    public PeriodicHandlerLooperScheduler(Looper looper) {
        mHandler = new PeriodicTaskHandler(looper);
    }

    public TaskInfo scheduleTask(TaskInfo taskInfo) {
        mHandler.scheduleTask(taskInfo, SystemClock.elapsedRealtime());
        return taskInfo;
    }

    public void stopTask(TaskInfo info){
        info.cancelled = true;
    }

    public interface Task {
        void doTask();
    }

    public static final class TaskInfo {
        final long interval;
        final Task task;
        volatile boolean cancelled;

        public TaskInfo(long interval, Task task) {
            this.interval = interval;
            this.task = task;
        }
    }

    private static class PeriodicTaskHandler extends Handler {

        public static final String LOG_TAG = "PeriodicTaskHandler";

        public PeriodicTaskHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            Log.d(LOG_TAG, "handleMessage()");

            if (msg.what != PERIODIC_TASK) return;
            long t = SystemClock.elapsedRealtime();

            TaskInfo taskInfo = (TaskInfo) msg.obj;
            if (!taskInfo.cancelled) {
                taskInfo.task.doTask();
                scheduleTask(taskInfo, t);
            }
        }

        public void scheduleTask(TaskInfo info, long t) {
            Message message = obtainMessage(PERIODIC_TASK, info);
            sendMessageAtTime(message, t + info.interval);
        }
    }
}
