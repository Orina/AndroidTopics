package com.topic.elmira.androidtopics.downloadimage;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannelGroup;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.topic.elmira.androidtopics.R;

/**
 * Created by Elmira on 3/16/2018.
 */

public class DownloadImageIntentService extends IntentService {

    public static final String LOG_TAG = "DownloadImageService";
    public static final String ARG_IMAGE_URL = "imageUrl";
    public static final String ARG_IMAGE_BITMAP = "imageUrl";
    public static final String ARG_DOWNLOAD_TYPE ="downloadType";
    public static final String ARG_RESULT_RECEIVER ="resultReceiver";
    public static final String NOTIFICATION_CHANNEL_ID = "downloadImageChannel";

    public static final int DOWNLOAD_TYPE_RETURN_NOTIFICATION_INTENT = 0;
    public static final int DOWNLOAD_TYPE_RETURN_RESULT_RECEIVER = 1;

    public DownloadImageIntentService() {
        super("DownloadImageIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable final Intent intent) {
        Log.d(LOG_TAG, "onHandleIntent()");
        String imageUrl = intent.getStringExtra(ARG_IMAGE_URL);
        final int downloadType = intent.getIntExtra(ARG_DOWNLOAD_TYPE, DOWNLOAD_TYPE_RETURN_NOTIFICATION_INTENT);

        Log.d(LOG_TAG, "imageUrl: "+imageUrl);

        NetworkUtil.getInstance().downloadImage(imageUrl, new NetworkUtil.DownloadImageCallback() {
            @Override
            public void onImageDownloaded(Bitmap bitmap) {
                Log.d(LOG_TAG,"onImageDownloaded()");

                if(downloadType==DOWNLOAD_TYPE_RETURN_NOTIFICATION_INTENT) {
                    Intent intent = new Intent(getBaseContext(), DownloadImageActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                    intent.putExtra(ARG_IMAGE_BITMAP, bitmap);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(),
                            DownloadImageActivity.DOWNLOAD_IMAGE_REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(
                            getApplicationContext(), NOTIFICATION_CHANNEL_ID);

                    builder.setSmallIcon(android.R.drawable.ic_dialog_alert);
                    builder.setContentTitle("The image was downloaded");
                    builder.setContentText("Successful downloaded image");
                    builder.setContentIntent(pendingIntent);

                    NotificationManagerCompat.from(getBaseContext()).notify(0, builder.build());
                }
                else if (downloadType == DOWNLOAD_TYPE_RETURN_RESULT_RECEIVER){
                    ResultReceiver resReceiver = intent.getParcelableExtra(ARG_RESULT_RECEIVER);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(ARG_IMAGE_BITMAP, bitmap);
                    resReceiver.send(DownloadImageActivity.DOWNLOAD_IMAGE_REQUEST_CODE,bundle);
                }
            }

            @Override
            public void onFailure() {
                Log.d(LOG_TAG, "onFailure()");
            }
        });
    }
}
