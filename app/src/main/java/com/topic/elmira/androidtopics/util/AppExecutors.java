package com.topic.elmira.androidtopics.util;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Elmira Andreeva on 5/23/18.
 */

public class AppExecutors {

    private static final int POOL_SIZE = 5;

    private static final ThreadPoolExecutor POOL_EXECUTOR;

    static {
        int n = Runtime.getRuntime().availableProcessors();

        POOL_EXECUTOR = new ThreadPoolExecutor(n * 2,
                n * 2,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>());

        POOL_EXECUTOR.allowCoreThreadTimeOut(true);
    }

    public static void submit(Runnable runnable) {
        POOL_EXECUTOR.execute(runnable);
    }
}