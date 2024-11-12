package com.example.myapplication.ui.actions;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class FetchImage extends Thread {
    Handler handler;
    String url;
    Bitmap bmp;
    ImageView image;
    public FetchImage(String url, ImageView image, Handler handler){
        this.image = image;
        this.url = url;
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            InputStream is = new java.net.URL(url).openStream();
            bmp = BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            Log.e("EventDetailedActivity", "Invalid URL: " + e.getMessage());
            Log.e("EventDetailedActivity", url);
            throw new RuntimeException(e);
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                image.setImageBitmap(bmp);
            }
        });
    }

}