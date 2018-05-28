package com.topic.elmira.androidtopics.mvp.model;

import android.util.ArraySet;

import com.topic.elmira.androidtopics.mvp.model.remote.RemoteDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elmira Andreeva on 5/24/18.
 */

public class Repository implements DataSource {

    private static Repository instance;

    private RemoteDataSource remoteDataSource;
    private ArraySet<Article> memoryCache = new ArraySet<Article>();

    private Repository() {
        this.remoteDataSource = RemoteDataSource.getInstance();
    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    @Override
    public void loadNews(final LoadNewsCallback callback) {

        if (!memoryCache.isEmpty()) {
            callback.onNewsLoaded(new ArrayList(memoryCache));
        }

        remoteDataSource.loadNews(new LoadNewsCallback() {
            @Override
            public void onNewsLoaded(List<Article> articles) {
                callback.onNewsLoaded(articles);
                memoryCache.addAll(articles);
            }

            @Override
            public void onFailure() {
                callback.onFailure();
            }
        });
    }
}
