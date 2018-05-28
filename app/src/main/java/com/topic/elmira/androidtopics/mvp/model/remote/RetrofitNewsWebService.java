package com.topic.elmira.androidtopics.mvp.model.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Elmira Andreeva on 5/24/18.
 */

public class RetrofitNewsWebService {

    private static NewsWebService retrofitInstance;

    public static NewsWebService getRetrofit(){
        if (retrofitInstance == null){
            retrofitInstance = new Retrofit.Builder().baseUrl(NewsWebService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create()).build().create(NewsWebService.class);
        }
        return retrofitInstance;
    }
}
