package com.example.myapplication.ui.actions;

import android.content.Context;
import android.content.Intent;

import com.example.myapplication.model.Event;
import com.example.myapplication.ui.activities.EventDetailedActivity;
import com.example.myapplication.ui.activities.TicketBuyActivity;

/**
 * Handles actions related to the shop.
 */
public class ShopActions {

    /**
     * Navigates to the detailed view of an event.
     *
     * @param ctx the context
     * @param event the event to view in detail
     */
    public static void navigateToDetailedView(Context ctx, Event event) {
        Intent intent = new Intent(ctx, EventDetailedActivity.class);
        intent.putExtra("event", event);
        ctx.startActivity(intent);
    }

    /**
     * Initiates the process of buying a ticket for an event.
     *
     * @param ctx the context
     * @param event the event for which to buy a ticket
     */
    public static void buyTicket(Context ctx, Event event) {
        Intent intent = new Intent(ctx, TicketBuyActivity.class);
        intent.putExtra("event", event);
        ctx.startActivity(intent);
    }
}