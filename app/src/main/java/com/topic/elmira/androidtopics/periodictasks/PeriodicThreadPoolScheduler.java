package com.topic.elmira.androidtopics.periodictasks;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Elmira Andreeva on 5/27/18.
 */

public class PeriodicThreadPoolScheduler {

    private ScheduledExecutorService executorService;

    private static PeriodicThreadPoolScheduler instance;

    private PeriodicThreadPoolScheduler() {
        int n = Runtime.getRuntime().availableProcessors();
        executorService = new ScheduledThreadPoolExecutor(n * 2);
    }

    public static PeriodicThreadPoolScheduler getInstance() {
        if (instance == null) {
            instance = new PeriodicThreadPoolScheduler();
        }
        return instance;
    }

    public void schedule(Runnable runnable, int interval) {
        executorService.schedule(runnable, interval, TimeUnit.SECONDS);
    }

    public void stop(){
        executorService.shutdown();
    }
}
