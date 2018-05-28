package com.topic.elmira.androidtopics.mvp.model;

import java.util.List;

/**
 * Created by Elmira Andreeva on 5/24/18.
 */

public interface DataSource {

    void loadNews(LoadNewsCallback callback);

    interface LoadNewsCallback{
        void onNewsLoaded(List<Article> articles);
        void onFailure();
    }

}
