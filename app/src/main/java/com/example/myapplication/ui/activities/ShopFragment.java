package com.example.myapplication.ui.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Event;
import com.example.myapplication.ui.EventsAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment {
    View view;
    RecyclerView recycler;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        List<Event> events = new ArrayList<Event>();
        for(int i = 0; i < 10; i++){
            events.add(Event.testEvent());
        }
        view = inflater.inflate(R.layout.fragment_shop, container, false);
        recycler = view.findViewById(R.id.Recycler);
        recycler.setAdapter(new EventsAdapter(events));
        return view;
    }

}
