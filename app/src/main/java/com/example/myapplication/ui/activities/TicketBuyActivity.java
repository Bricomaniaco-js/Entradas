package com.example.myapplication.ui.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.model.Event;
import com.example.myapplication.ui.actions.ShopActions;
import com.example.myapplication.ui.actions.UserActions;

public class TicketBuyActivity extends AppCompatActivity {
    Event event;
    Button buyButton;
    Button cancelButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        event = (Event) getIntent().getSerializableExtra("event");
        setContentView(R.layout.activity_buy_ticket);
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
        ((TextView)findViewById(R.id.text_title)).setText(event.getName());
    }
    private void buyTicket() {
        UserActions.buyTicket(event);
        Toast.makeText(this, "Entrada comprada con éxito", Toast.LENGTH_SHORT).show();
        ShopActions.navigateToDetailedView(this, event);
    }
}
