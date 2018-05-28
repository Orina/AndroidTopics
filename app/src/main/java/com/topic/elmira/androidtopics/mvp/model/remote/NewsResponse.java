package com.topic.elmira.androidtopics.mvp.model.remote;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Elmira Andreeva on 5/24/18.
 */

public class NewsResponse {

    @SerializedName("response")
    public Response response;

    public static class Response{

        @SerializedName("docs")
        public List<Article> articles;
    }

    public static class Article{
        public String id;

        @SerializedName("web_url")
        public String webUrl;

        @SerializedName("headline")
        public HeadLine headLine;

        @SerializedName("pub_date")
        public String pubDate;

        public static class HeadLine{
            @SerializedName("main")
            public String message;
        }
    }
}
