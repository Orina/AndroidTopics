package com.topic.elmira.androidtopics.executors;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

/**
 * Created by Elmira Andreeva on 5/24/18.
 */

public class ImageDownloadTask implements Callable<Bitmap> {
    private String image_url;
    public static final String LOG_TAG = "ImgDownloadTask";

    public ImageDownloadTask(String url) {
        this.image_url = url;
    }

    @Override
    public Bitmap call() throws Exception {
        return downloadRemoteImage2();
    }

    private Bitmap downloadRemoteImage() {
        InputStream inputStream = null;
        Bitmap bitmap = null;
        try {
            URL url = new URL(image_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            }
            return bitmap;
        } catch (Throwable ex) {
            Log.e(LOG_TAG, ex.getMessage(), ex);
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable ignored) {
                }
            }
        }
    }

    private Bitmap downloadRemoteImage2() {
        try {
            return BitmapFactory.decodeStream((InputStream) new URL(image_url).getContent());
        } catch (Throwable ex) {
            Log.e(LOG_TAG, ex.getMessage(), ex);
            return null;
        }
    }
}
