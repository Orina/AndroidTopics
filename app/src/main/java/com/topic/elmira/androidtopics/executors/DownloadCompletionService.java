package com.topic.elmira.androidtopics.executors;

import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;

/**
 * Created by Elmira Andreeva on 5/24/18.
 */

public class DownloadCompletionService extends ExecutorCompletionService {

    private ExecutorService mExecutor;

    public DownloadCompletionService(ExecutorService executor) {
        super(executor);
        mExecutor = executor;
    }

    public void shutdown() {
        mExecutor.shutdown();
    }

    public boolean isTerminated() {
        return mExecutor.isTerminated();
    }
}
