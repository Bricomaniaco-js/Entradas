package com.example.myapplication.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.UserManager.UserManager;
import com.example.myapplication.model.Event;
import com.example.myapplication.model.User;
import com.example.myapplication.ui.actions.ShopActions;
import com.example.myapplication.ui.actions.UserActions;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class TicketBuyActivity extends AppCompatActivity {
    Event event;
    Button buyButton;
    Button cancelButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        event = (Event) getIntent().getSerializableExtra("event");
        setContentView(R.layout.activity_buy_ticket);

        TextView title = findViewById(R.id.text_title);
        title.setText(event.getName());

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

        buyButton = findViewById(R.id.button_buy);
        cancelButton = findViewById(R.id.button_cancel);
        buyButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buyTicket();
                    }
                });
        cancelButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShopActions.navigateToDetailedView(v.getContext(), event);
                    }
                });

    }
    private void buyTicket() {
        UserActions.buyTicket(event, new UserActions.UserCallback() {
            @Override
            public void onSuccess(User user) {
                Toast.makeText(TicketBuyActivity.this, "Ticket Bought Successfully", Toast.LENGTH_SHORT).show();
                ShopActions.navigateToDetailedView(TicketBuyActivity.this, event);
            }

            @Override
            public void onFailure() {
                Toast.makeText(TicketBuyActivity.this, "Sorry, Ticket not bought", Toast.LENGTH_SHORT).show();
                ShopActions.navigateToDetailedView(TicketBuyActivity.this, event);
            }
        });

    }
}
