package com.example.myapplication.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.helper.widget.Carousel;

import com.example.myapplication.R;
import com.example.myapplication.model.Event;

import java.io.IOException;
import java.io.InputStream;

public class ImageCarouselAdapter implements Carousel.Adapter {
    Event event;
    Handler handler;

    public ImageCarouselAdapter(Event e) {
        this.event = e;
    }
    @Override
    public int count() {
        return event.getImages().size();
    }

    @Override
    public void populate(View view, int index) {
        handler = new Handler();
        ImageView image = (ImageView) view;
        new FetchImage(event.getImages().get(index), image).start();;

    }

    @Override
    public void onNewItem(int index) {

    }

    class FetchImage extends Thread {
        String url;
        Bitmap bmp;
        ImageView image;
        public FetchImage(String url, ImageView image){
            this.image = image;
            this.url = url;
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
}
