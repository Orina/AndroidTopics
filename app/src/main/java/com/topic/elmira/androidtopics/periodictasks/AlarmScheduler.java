package com.topic.elmira.androidtopics.periodictasks;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import com.topic.elmira.androidtopics.R;

import java.util.concurrent.TimeUnit;

/**
 * Created by Elmira Andreeva on 5/27/18.
 */

public class AlarmScheduler extends IntentService {

    public static final String LOG_TAG = "AlarmScheduler";
    public static final int SAMPLE_TASK = 1;
    public static final String PARAM_TASK = "Scheduler.TASK";

    public AlarmScheduler() {
        super("AlarmScheduler");
    }

    public static void startTask(Context cntx) {
        int interval = cntx.getResources().getInteger(R.integer.alarm_interval);
        ((AlarmManager) cntx.getSystemService(Context.ALARM_SERVICE))
                .setInexactRepeating(
                        AlarmManager.ELAPSED_REALTIME,
                        SystemClock.elapsedRealtime() + 100,
                        TimeUnit.MILLISECONDS.convert(interval, TimeUnit.MINUTES),
                        getTaskIntent(cntx, SAMPLE_TASK)
                );
    }

    public static void stopTask(Context cntx) {
        ((AlarmManager) cntx.getSystemService(ALARM_SERVICE))
                .cancel(getTaskIntent(cntx, SAMPLE_TASK));
    }

    private static PendingIntent getTaskIntent(Context cntx, int taskId) {
        Intent intent = new Intent(cntx, AlarmScheduler.class);
        intent.putExtra(PARAM_TASK, taskId);

        return PendingIntent.getService(cntx,
                taskId,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int taskParam = intent.getIntExtra(PARAM_TASK, -1);
        Log.d(LOG_TAG, "onHandleIntent() param: "+ taskParam);
        SystemClock.sleep(1000);
        Log.d(LOG_TAG, "onHandleIntent() stop");
    }
}
