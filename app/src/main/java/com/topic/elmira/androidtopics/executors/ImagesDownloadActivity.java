package com.topic.elmira.androidtopics.executors;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ImagesDownloadActivity extends AppCompatActivity {

    private LinearLayout layoutImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_images_download);

        layoutImages = new LinearLayout(this);
        layoutImages.setOrientation(LinearLayout.VERTICAL);
        layoutImages.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        setContentView(layoutImages);

        DownloadCompletionService ecs = new DownloadCompletionService(Executors.newCachedThreadPool());
        new ConsumerThread(ecs).start();

        String[] urls = {"https://static.eharmony.com/files/us/images/landing/local-dating-state-utah.jpg",
        "https://www.kimptonhotels.com/blog/wp-content/uploads/2015/06/9070717998_9be25896ca_k.jpg",
        "http://onebigphoto.com/uploads/2012/03/waves-of-utah.jpg",
        "https://i.pinimg.com/originals/76/70/86/767086073e255f7695105fbb624ecb71.jpg",
        "https://deepfrogphoto.com/Brett-Pelletier-Photography/National-Parks-Gallery/Arches-National-Park-Gallery/slides/turret-arch-and-la-sal-mountains.jpg"};

        for (int i=0; i< urls.length ; i++){
            ecs.submit(new ImageDownloadTask(urls[i]));
        }

        ecs.shutdown();
    }

    private void addImage(final Bitmap image){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ImageView iv = new ImageView(ImagesDownloadActivity.this);
                iv.setImageBitmap(image);
                layoutImages.addView(iv);
                iv.animate().alpha(1).start();
            }
        });
    }

    private class ConsumerThread extends Thread{
        private DownloadCompletionService mExecutorService;

        public ConsumerThread(DownloadCompletionService mExecutorService) {
            this.mExecutorService = mExecutorService;
        }

        @Override
        public void run() {
            super.run();
            try {
                while (!mExecutorService.isTerminated()){
                    Future<Bitmap> future = mExecutorService.poll(1, TimeUnit.SECONDS);
                    if (future!=null){
                        addImage(future.get());
                    }
                }
            }
            catch (Throwable ex){
                ex.printStackTrace();
            }
        }
    }
}
