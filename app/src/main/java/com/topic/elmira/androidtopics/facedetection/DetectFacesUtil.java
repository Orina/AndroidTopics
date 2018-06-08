package com.topic.elmira.androidtopics.facedetection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.SparseArray;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

/**
 * Created by Elmira Andreeva on 6/5/18.
 */

public class DetectFacesUtil {

    private static DetectFacesUtil instance;

    private DetectFacesUtil(){

    }

    public static DetectFacesUtil getInstance(){
        if(instance==null){
            instance = new DetectFacesUtil();
        }
        return instance;
    }

    public SparseArray<Face> detectFace(Context context, int imageId) throws Exception{
        FaceDetector faceDetector = new
                FaceDetector.Builder(context).setTrackingEnabled(false)
                .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                .build();
        if(!faceDetector.isOperational()){
            return null;
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable=true;

        Bitmap myBitmap = BitmapFactory.decodeResource(
                context.getResources(),
                imageId,
                options);

        Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
        return  faceDetector.detect(frame);
    }
}
