package com.example.myapplication.ui;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Event;
import com.example.myapplication.ui.actions.FetchImage;
import com.example.myapplication.ui.activities.EventDetailedActivity;
import com.example.myapplication.ui.actions.ShopActions;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder> {
    private List<Event> events;
    Handler handler;
    public EventsAdapter(List<Event> events){

        this.events = events;
    }
    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.shop_product_fragment, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Handler handler = new Handler();

        Event event = events.get(position);
        TextView title = (TextView)holder.itemView.findViewById(R.id.ItemTitle);

        title.setText(event.getName());
        TextView description = (TextView)holder.itemView.findViewById(R.id.ItemDescription);
        description.setText(event.getDescription());

        TextView price = (TextView)holder.itemView.findViewById(R.id.ItemPrice);
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        DecimalFormat decimalFormat = new DecimalFormat("#.00", symbols);
        price.setText(decimalFormat.format(event.getPrice()) + "€");

        ImageView image = (ImageView)holder.itemView.findViewById(R.id.ItemImage);
        new FetchImage(event.getImages().get(0), image, handler).start();
        holder.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShopActions.navigateToDetailedView(v.getContext(), event);
                    }
                }
        );
    }


    @Override
    public int getItemCount() {
        return events.size();
    }


    static class EventViewHolder extends RecyclerView.ViewHolder {

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
