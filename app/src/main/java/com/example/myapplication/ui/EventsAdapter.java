package com.example.myapplication.ui;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Event;
import com.example.myapplication.ui.activities.EventDetailedActivity;

import java.util.ArrayList;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder> {
    private List<Event> events;
    public EventsAdapter(){
        events = new ArrayList<Event>();
        ArrayList<String> images = new ArrayList<String>();
        images.add("https://derpicdn.net/img/view/2022/7/1/2898577.png");
        images.add("https://derpicdn.net/img/view/2021/4/15/2593604.jpg");
        images.add("https://derpicdn.net/img/view/2022/5/9/2861888.jpg");
        for(int i = 0; i < 10; i++){
            Event e =new Event(123, "Evento " + i, "Descripcion 1", null);
            e.setImages(images);
            events.add(e);

        }

    }
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
        TextView title = (TextView)holder.itemView.findViewById(R.id.ItemTitle);
        title.setText(events.get(position).getName());
        holder.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        navigateToDetailedView(v, events.get(position));
                    }
                }
        );
    }


    @Override
    public int getItemCount() {
        return events.size();
    }

    private void navigateToDetailedView(View v, Event event) {
        //TODO: pasarlo a shopActions o algo asi
        Intent intent = new Intent(v.getContext(), EventDetailedActivity.class);
        intent.putExtra("event", event);
        v.getContext().startActivity(intent);
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
