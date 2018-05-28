package com.topic.elmira.androidtopics.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Elmira Andreeva on 5/23/18.
 */

public class Article {

   Headline headline;

   @SerializedName("web_url")
   String webUrl;

   String source;

   @SerializedName("id")
   String id;

    public Headline getHeadline() {
        return headline;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getSource() {
        return source;
    }

    public String getId() {
        return id;
    }

    class Headline{
        String main;

        public String getMain() {
            return main;
        }
    }
}
