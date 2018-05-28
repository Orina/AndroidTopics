package com.topic.elmira.androidtopics.mvp.model;

import com.topic.elmira.androidtopics.mvp.model.remote.NewsResponse;

/**
 * Created by Elmira Andreeva on 5/24/18.
 */

public class Article {

    private String id;
    private String message;
    private String url;
    private String date;
    private boolean favorite;
    private String title;

    public Article(NewsResponse.Article response){
        this.id = response.id;
        this.message = response.headLine.message;
        this.url = response.webUrl;
        this.date = response.pubDate;
        this.title = message.substring(0, Math.min(50, message.length()));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
