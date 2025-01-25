package com.example.myapplication.ui;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.App;
import com.example.myapplication.R;
import com.example.myapplication.model.Event;
import com.example.myapplication.model.Ticket;
import com.example.myapplication.ui.actions.ShopActions;
import com.example.myapplication.ui.activities.ProfileFragment;
import com.example.myapplication.ui.activities.QRCodeActivity;

import java.util.ArrayList;
import java.util.List;

public class TicketsAdapter extends RecyclerView.Adapter {
    private ArrayList<Event> events;
    private ArrayList<Ticket> tickets;

    public TicketsAdapter(List<Event> events, List<Ticket> tickets) {
        this.events = new ArrayList<>(events);
        this.tickets = new ArrayList<>(tickets);
    }

    public TicketsAdapter() {
        this.events = new ArrayList<>();
        this.tickets = new ArrayList<>();
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.ticket_fragment, parent, false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Ticket ticket = tickets.get(position);
        Event event = events.get(position);
        TicketViewHolder ticketViewHolder = (TicketViewHolder) holder;
        ((TextView)ticketViewHolder.itemView.findViewById(R.id.ticket_title)).setText(event.getName());
        ((TextView)ticketViewHolder.itemView.findViewById(R.id.ticket_date)).setText(event.getDate());
        holder.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showQRCode(ticket.getId().toHexString());
                    }
                }
        );

    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    public void addEvent(Event event, Ticket ticket) {
        events.add(event);
        tickets.add(ticket);
        notifyItemInserted(events.size() - 1);


    }

    public static class TicketViewHolder extends RecyclerView.ViewHolder {
        TextView ticketTitle;
        TextView ticketDate;

        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            ticketTitle = itemView.findViewById(R.id.ticket_title);
            ticketDate = itemView.findViewById(R.id.ticket_date);
        }
    }

    public void showQRCode(String ticketId) {
        Intent intent = new Intent(App.getInstance().getBaseContext(), QRCodeActivity.class);
        intent.putExtra("QR_CONTENT", "Your QR code content here");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        App.getInstance().getBaseContext().startActivity(intent);
    }
}
