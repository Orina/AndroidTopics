package com.topic.elmira.androidtopics.mvp.model.remote;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Elmira Andreeva on 5/24/18.
 */

public class RetrofiltNewsWebService {

    private static Retrofit retrofitInstance;

    public static Retrofit getRetrofit(){
        if (retrofitInstance == null){
            retrofitInstance = new Retrofit.Builder().baseUrl(NewsWebService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create())).build();
        }
        return retrofitInstance;
    }
}
