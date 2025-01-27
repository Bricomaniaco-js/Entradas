package com.example.myapplication.ui.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.App;
import com.example.myapplication.R;
import com.example.myapplication.db.ApiController;
import com.example.myapplication.model.Event;
import com.example.myapplication.model.Ticket;
import com.example.myapplication.model.User;
import com.example.myapplication.ui.TicketsAdapter;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {
    ArrayList<Event> availableEvents;
    TicketsAdapter ticketsAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        availableEvents = new ArrayList<>();
        ApiController controller = App.getInstance().getController();
        User u = App.getInstance().getCurrentUser();
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        RecyclerView recycler = view.findViewById(R.id.ticketRecycler);
        ticketsAdapter = new TicketsAdapter();
        recycler.setAdapter(ticketsAdapter);
        for(Ticket t : u.getTickets()) {
            if(t.isValid()) {
                controller.getEvent(t.getEventId().toHexString(), new ApiController.EventCallback() {
                    @Override
                    public void onSuccess(Event event) {
                        ticketsAdapter.addEvent(event, t);
                        recycler.getAdapter().notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        System.out.println("Failed to retrieve event" + t);
                    }
                });
            }
        }


        return view;
    }
}
