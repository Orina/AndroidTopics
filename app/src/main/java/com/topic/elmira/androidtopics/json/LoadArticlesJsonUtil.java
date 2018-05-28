package com.topic.elmira.androidtopics.json;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Elmira Andreeva on 5/23/18.
 */

public class LoadArticlesJsonUtil {

    public static final String FILE_NAME = "articles.json";

    public static List<Article> readArticleFromJsonFile(Context context) throws IOException {
        InputStream is = context.getAssets().open(FILE_NAME);
        InputStreamReader reader = new InputStreamReader(is);

        JsonResponse response = new Gson().fromJson(reader, JsonResponse.class);
        return response.getResponse().getArticles();
    }
}
