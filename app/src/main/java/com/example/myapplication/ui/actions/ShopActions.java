package com.example.myapplication.ui.actions;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;

import com.example.myapplication.model.Event;
import com.example.myapplication.ui.activities.EventDetailedActivity;

public class ShopActions {
    public void navigateToEvent(Context ctx, Event event) {
        Intent intent = new Intent(ctx, EventDetailedActivity.class);
        intent.putExtra("event", event);
        //startActivity(intent);
    }
}
