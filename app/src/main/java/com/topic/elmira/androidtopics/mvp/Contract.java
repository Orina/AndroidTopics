package com.topic.elmira.androidtopics.mvp;

import com.topic.elmira.androidtopics.mvp.model.Article;

import java.util.List;

/**
 * Created by Elmira Andreeva on 5/24/18.
 */

public interface Contract {

    interface View{

        void showLoadingIndicator(boolean isLoading);

        void showNews(List<Article> articles);

        void showFailure();

        boolean isActive();
    }

    interface Presenter{

        void loadNews();
    }
}
