package com.example.myapplication.ui.actions;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;

import com.example.myapplication.model.Event;
import com.example.myapplication.ui.activities.EventDetailedActivity;
import com.example.myapplication.ui.activities.TicketBuyActivity;

public class ShopActions {
    public static void navigateToDetailedView(Context ctx, Event event) {
        Intent intent = new Intent(ctx, EventDetailedActivity.class);
        intent.putExtra("event", event);
        ctx.startActivity(intent);
    }

    public static void buyTicket(Context ctx, Event event) {
        Intent intent = new Intent(ctx, TicketBuyActivity.class);
        intent.putExtra("event", event);
        ctx.startActivity(intent);
    }
}
