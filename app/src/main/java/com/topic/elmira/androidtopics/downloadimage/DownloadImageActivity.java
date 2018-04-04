package com.topic.elmira.androidtopics.downloadimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.topic.elmira.androidtopics.R;

import java.lang.ref.WeakReference;

public class DownloadImageActivity extends AppCompatActivity {
    public static final String LOG_TAG = "DownloadImageActivity";

    public static final int DOWNLOAD_IMAGE_REQUEST_CODE = 11;
    public static final String IMAGE_URL = "http://www.barbie-collectible.com/wp-content/uploads/2017/03/Barbie-Happy-Birthday-Doll-4.jpg";

    ImageView mImageView;
    private ResultReceiver mResultReceiver;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_image);
        mImageView = findViewById(R.id.imageView);

        mHandler = new Handler();
        mResultReceiver = new DownloadedImageResultReceiver(mHandler, this);

        Intent intent = getIntent();
        Log.d(LOG_TAG, "intent: " + intent);
        if (intent != null) {
            Bitmap bitmap = intent.getParcelableExtra(DownloadImageIntentService.ARG_IMAGE_BITMAP);
            Log.d(LOG_TAG, "bitmap is null? " + (bitmap == null));
            if (bitmap != null) {
                mImageView.setImageBitmap(bitmap);
            }
        }
    }

    public void downloadImage(View view) {
        Intent intent = new Intent(this, DownloadImageIntentService.class);
        intent.putExtra(DownloadImageIntentService.ARG_IMAGE_URL, IMAGE_URL);
        startService(intent);
    }

    public void downloadImageWithResultReceiver(View view) {
        Intent intent = new Intent(this, DownloadImageIntentService.class);
        intent.putExtra(DownloadImageIntentService.ARG_IMAGE_URL, IMAGE_URL);
        intent.putExtra(DownloadImageIntentService.ARG_DOWNLOAD_TYPE, DownloadImageIntentService.DOWNLOAD_TYPE_RETURN_RESULT_RECEIVER);
        intent.putExtra(DownloadImageIntentService.ARG_RESULT_RECEIVER, mResultReceiver);
        startService(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.d(LOG_TAG, "onNewIntent()");
        super.onNewIntent(intent);

        Bitmap bitmap = intent.getParcelableExtra(DownloadImageIntentService.ARG_IMAGE_BITMAP);
        Log.d(LOG_TAG, "bitmap is null? " + (bitmap == null));
        if (bitmap != null) {
            mImageView.setImageBitmap(bitmap);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(LOG_TAG, "onActivityResult()");
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == DOWNLOAD_IMAGE_REQUEST_CODE) {
            Bitmap bitmap = data.getParcelableExtra(DownloadImageIntentService.ARG_IMAGE_BITMAP);
            if (bitmap != null) {
                mImageView.setImageBitmap(bitmap);
            }
        }
    }

    public void updateImage(Bitmap bitmap){
        if (bitmap == null) return;
        mImageView.setImageBitmap(bitmap);
    }

    private static class DownloadedImageResultReceiver extends ResultReceiver {

        private WeakReference<DownloadImageActivity> mActivity;

        public DownloadedImageResultReceiver(Handler handler, DownloadImageActivity activity) {
            super(handler);
            mActivity = new WeakReference<DownloadImageActivity>(activity);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            Log.d(LOG_TAG, "onReceiveResult()");
            super.onReceiveResult(resultCode, resultData);
            DownloadImageActivity activity = mActivity.get();
            if (activity==null) return;

            Bitmap bitmap = resultData.getParcelable(DownloadImageIntentService.ARG_IMAGE_BITMAP);
            activity.updateImage(bitmap);
        }
    }
}
