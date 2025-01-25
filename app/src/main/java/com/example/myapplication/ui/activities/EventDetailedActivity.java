package com.example.myapplication.ui.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.regex.Pattern;

public class EventDetailedActivity extends AppCompatActivity {
    Event event;
    Carousel carousel;
    FloatingActionButton fab;
    Handler handler;
    Runnable runnable;
    int currentIndex = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detailed);
        event = (Event) getIntent().getSerializableExtra("event");

        TextView title = findViewById(R.id.titleText);
        title.setText(event.getName());

        TextView description = findViewById(R.id.descriptionText);
        description.setText(event.getDescription());

        TextView price = findViewById(R.id.text_price);
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        DecimalFormat decimalFormat = new DecimalFormat("#.00", symbols);
        price.setText(decimalFormat.format(event.getPrice()) + "€");

        TextView date = findViewById(R.id.text_date);
        date.setText(event.getDate().toString());

        TextView location = findViewById(R.id.text_location);
        location.setText(event.getLocation());
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = event.getLocation();
                String uri = "geo:0,0?q=" + address;
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });




        carousel = findViewById(R.id.carousel);
        carousel.setAdapter(new ImageCarouselAdapter(event));

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                currentIndex = (currentIndex + 1) % event.getImages().size();
                carousel.transitionToIndex(currentIndex, 500);
                handler.postDelayed(this, 3000); // Change image every 3 seconds
            }
        };
        handler.postDelayed(runnable, 3000);
        //todo: ae vuelven a descargar las imagenes cada vez que se cambia de imagen.
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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
