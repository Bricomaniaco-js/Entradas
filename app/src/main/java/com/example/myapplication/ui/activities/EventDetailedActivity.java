package com.example.myapplication.ui.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Carousel;

import com.example.myapplication.R;
import com.example.myapplication.model.Event;
import com.example.myapplication.ui.ImageCarouselAdapter;
import com.example.myapplication.ui.actions.ShopActions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.io.InputStream;

public class EventDetailedActivity extends AppCompatActivity {
    Event event;
    Carousel carousel;
    FloatingActionButton fab;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detailed);
        event = (Event) getIntent().getSerializableExtra("event");
        carousel = findViewById(R.id.carousel);
        carousel.setAdapter(new ImageCarouselAdapter(event));
        //TODO: el carrusel no funciona, carga las imagenes pero no funcionan las transiciones.
        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShopActions.buyTicket(EventDetailedActivity.this, event);
                }
        }
        );

    }
}
