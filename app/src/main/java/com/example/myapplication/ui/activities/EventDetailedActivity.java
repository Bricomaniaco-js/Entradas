package com.example.myapplication.ui.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Carousel;

import com.example.myapplication.R;
import com.example.myapplication.model.Event;
import com.example.myapplication.ui.ImageCarouselAdapter;

import java.io.IOException;
import java.io.InputStream;

public class EventDetailedActivity extends AppCompatActivity {
    Event event;
    Carousel carousel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detailed);
        event = (Event) getIntent().getSerializableExtra("event");
        carousel = findViewById(R.id.carousel);
        carousel.setAdapter(new ImageCarouselAdapter(event));

    }
}
