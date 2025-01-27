package com.example.myapplication.ui.actions;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.example.myapplication.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * Fetches an image from a URL and sets it to an ImageView.
 */
public class FetchImage extends Thread {
    private Handler handler;
    private String url;
    private Bitmap bmp;
    private ImageView image;

    /**
     * Constructs a new FetchImage thread.
     *
     * @param url the URL of the image to fetch
     * @param image the ImageView to set the fetched image
     * @param handler the handler to post the result to the main thread
     */
    public FetchImage(String url, ImageView image, Handler handler) {
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
            bmp = BitmapFactory.decodeResource(image.getResources(), R.drawable.not_found);
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                image.setImageBitmap(bmp);
            }
        });
    }
}