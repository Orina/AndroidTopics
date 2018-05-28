package com.topic.elmira.androidtopics.json;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Elmira Andreeva on 5/23/18.
 */

public class ArticlesHandlerThread extends HandlerThread {

    public static final String LOG_TAG = "ArticlesHandlerThread";

    private Handler mHandler;
    private Handler mUiHandler;
    private WeakReference<Context> contextWeakReference;

    public static final int READ = 0;

    public ArticlesHandlerThread(Context context, Handler ui) {
        super("ArticlesHandlerThread", Process.THREAD_PRIORITY_BACKGROUND);
        mUiHandler = ui;
        contextWeakReference = new WeakReference<Context>(context);
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        mHandler = new Handler(getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == READ) {
                    Context context = contextWeakReference.get();
                    if (context == null) return;

                    try {
                        List<Article> articles = LoadArticlesJsonUtil.readArticleFromJsonFile(context);
                        mUiHandler.sendMessage(mUiHandler.obtainMessage(0, articles));
                    } catch (Throwable ex) {
                        Log.e(LOG_TAG, ex.getMessage(), ex);
                    }
                }
            }
        };
    }

    public void readArticles() {
        mHandler.sendEmptyMessage(READ);
    }
}
