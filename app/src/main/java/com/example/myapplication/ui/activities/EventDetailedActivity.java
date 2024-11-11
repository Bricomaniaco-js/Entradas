package com.example.myapplication.ui.activities;

import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Carousel;

import com.example.myapplication.R;
import com.example.myapplication.model.Event;
import com.example.myapplication.ui.ImageCarouselAdapter;

public class EventDetailedActivity extends AppCompatActivity {
    Event event;
    Carousel carousel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_detailed_activity);
        event = (Event) getIntent().getSerializableExtra("event");
        carousel = findViewById(R.id.carousel);
        carousel.setAdapter(new ImageCarouselAdapter(event));


    }
}
