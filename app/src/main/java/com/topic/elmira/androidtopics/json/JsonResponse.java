package com.topic.elmira.androidtopics.json;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Elmira Andreeva on 5/23/18.
 */

public class JsonResponse {

    @SerializedName("response")
    Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    class Response {
        @SerializedName("docs")
        List<Article> articles;

        public List<Article> getArticles() {
            return articles;
        }

        public void setArticles(List<Article> articles) {
            this.articles = articles;
        }
    }

}
