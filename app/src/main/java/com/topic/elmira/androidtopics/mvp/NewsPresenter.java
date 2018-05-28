package com.topic.elmira.androidtopics.mvp;

import com.topic.elmira.androidtopics.mvp.model.Article;
import com.topic.elmira.androidtopics.mvp.model.DataSource;
import com.topic.elmira.androidtopics.mvp.model.Repository;

import java.util.List;

/**
 * Created by Elmira Andreeva on 5/24/18.
 */

public class NewsPresenter implements Contract.Presenter {

    private Contract.View mView;
    private Repository mRepository;

    public NewsPresenter(Contract.View mView, Repository mRepository) {
        this.mView = mView;
        this.mRepository = mRepository;
    }

    @Override
    public void loadNews() {
        mView.showLoadingIndicator(true);

        mRepository.loadNews(new DataSource.LoadNewsCallback() {
            @Override
            public void onNewsLoaded(List<Article> articles) {
                if (!mView.isActive()) return;

                mView.showLoadingIndicator(false);
                mView.showNews(articles);
            }

            @Override
            public void onFailure() {
                if (!mView.isActive()) return;

                mView.showLoadingIndicator(false);
                mView.showFailure();
            }
        });
    }
}