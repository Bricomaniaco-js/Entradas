package com.example.myapplication.ui;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.helper.widget.Carousel;

import com.example.myapplication.model.Event;

public class ImageCarouselAdapter implements Carousel.Adapter {
    Event event;

    public ImageCarouselAdapter(Event e) {
        this.event = e;
    }
    @Override
    public int count() {
        return event.getImages().size();
    }

    @Override
    public void populate(View view, int index) {
        ((ImageView)(view)).setImageURI(Uri.parse(event.getImages().get(index)));

    }

    @Override
    public void onNewItem(int index) {

    }
}
