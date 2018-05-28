package com.topic.elmira.androidtopics.json;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.topic.elmira.androidtopics.R;
import com.topic.elmira.androidtopics.util.AppExecutors;

import java.util.List;

public class LoadArticlesActivity extends AppCompatActivity {
    public static final String LOG_TAG = "LoadArticlesActivity";

    private Handler uiHandler;
    private ArticlesHandlerThread mHandlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_articles);
        setupHandler();

        mHandlerThread = new ArticlesHandlerThread(this, uiHandler);
        mHandlerThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandlerThread.quit();
    }

    private void setupHandler(){
        uiHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0){
                    List<Article> articles = (List<Article>)msg.obj;
                    Log.d(LOG_TAG, "Loaded articles size: "+ articles.size());
                    Toast.makeText(LoadArticlesActivity.this, "Articles were loaded!", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }


    public void onLoad(View view){
        mHandlerThread.readArticles();
    }

    public void showMessage(final String text){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoadArticlesActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onLoadExecutor(View view){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    List<Article> articles = LoadArticlesJsonUtil.readArticleFromJsonFile(LoadArticlesActivity.this.getApplicationContext());
                    Log.d(LOG_TAG, "Loaded articles size: " + articles.size());
                    showMessage("Articles were loaded from Executor!");
                }
                catch (Throwable ex){
                    Log.e(LOG_TAG, ex.getMessage(), ex);
                }
            }
        };
        AppExecutors.submit(runnable);
    }
}
