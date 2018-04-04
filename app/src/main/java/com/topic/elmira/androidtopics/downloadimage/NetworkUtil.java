package com.topic.elmira.androidtopics.downloadimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Elmira on 3/16/2018.
 */

public class NetworkUtil {

    public static final String LOG_TAG = "NetworkUtil";
    private static NetworkUtil instance = new NetworkUtil();

    private NetworkUtil() {

    }

    public static NetworkUtil getInstance() {
        return instance;
    }

    public void downloadImage(String strUrl, DownloadImageCallback callback) {
        Log.d(LOG_TAG, "downloadImage(), url: "+strUrl);
        Bitmap bitmap = null;
        InputStream inputStream = null;
        try {
            URL url = new URL(strUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            int code = connection.getResponseCode();
            Log.d(LOG_TAG, "response code: "+code);
            if (code == HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
                callback.onImageDownloaded(bitmap);
            }
        } catch (Throwable ex) {
            Log.d(LOG_TAG, ex.getMessage(), ex);
            callback.onFailure();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable ignore) {

                }
            }
        }

    }

    public interface DownloadImageCallback {
        void onImageDownloaded(Bitmap bitmap);

        void onFailure();
    }
}
