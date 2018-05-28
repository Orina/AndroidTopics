package com.topic.elmira.androidtopics.mvp.model.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Elmira Andreeva on 5/24/18.
 */

public interface NewsWebService {

    String ENDPOINT = "https://api.nytimes.com/svc/search/v2/";

    @GET("articlesearch.json")
    Call<NewsResponse> loadNews(@Query("api-key") String apiKey,
                                @Query("fq") String newsDesk);

}
