package com.topic.elmira.androidtopics.mvp.model.remote;

import com.topic.elmira.androidtopics.mvp.model.Article;
import com.topic.elmira.androidtopics.mvp.model.DataSource;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Elmira Andreeva on 5/24/18.
 */

public class RemoteDataSource implements DataSource {

    private static final String API_KEY = "1ad66c23b17040b19151a8dae2e826a4";

    private static RemoteDataSource instance;
    private NewsWebService newsWebService;

    private RemoteDataSource() {
        newsWebService = RetrofitNewsWebService.getRetrofit();
    }

    public static RemoteDataSource getInstance() {
        if (instance == null) {
            instance = new RemoteDataSource();
        }
        return instance;
    }

    @Override
    public void loadNews(final LoadNewsCallback callback) {
        Call<NewsResponse> call = newsWebService.loadNews(API_KEY, "fashion");

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {

                List<Article> res = new ArrayList();
                if (response.body() == null) callback.onNewsLoaded(res);

                for (NewsResponse.Article responseArticle : response.body().response.articles) {
                    res.add(new Article(responseArticle));
                }
                callback.onNewsLoaded(res);
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                callback.onFailure();
            }
        });
    }
}
