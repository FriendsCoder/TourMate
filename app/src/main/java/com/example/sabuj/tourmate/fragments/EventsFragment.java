package com.example.sabuj.tourmate.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.sabuj.tourmate.R;
import com.example.sabuj.tourmate.adapters.EventsAdapter;
import com.example.sabuj.tourmate.models.Common;
import com.example.sabuj.tourmate.models.Event;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class EventsFragment extends Fragment {
    private DatabaseReference refEvent;
    private DatabaseReference refUserEvent;
    ArrayList<Event> eventsList;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    private FloatingActionButton fabEvent;
    EventsAdapter eventsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        initialization(view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refEvent = FirebaseDatabase.getInstance().getReference("Events");
        String user = Common.currentUser.getUserName();
        refUserEvent = refEvent.child(user);

        Wave wave = new Wave();
        progressBar.setIndeterminateDrawable(wave);
        progressBar.setVisibility(View.VISIBLE);
        eventsList = new ArrayList<>();
        //  eventsList.clear();
        refUserEvent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.INVISIBLE);
                for (DataSnapshot dataEvent : dataSnapshot.getChildren()) {
                    Event event = dataEvent.getValue(Event.class);
                    eventsList.add(event);
                }

                if (eventsList != null && eventsList.size() > 0) {
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    eventsAdapter = new EventsAdapter(getContext(), eventsList);
                    recyclerView.setAdapter(eventsAdapter);
                    eventsAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        fabEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEventFragment();
            }
        });

    }


    private void initialization(View view) {
        fabEvent = view.findViewById(R.id.fab_event);
        recyclerView = view.findViewById(R.id.rv_events);
        progressBar = view.findViewById(R.id.spin_progress);
    }

    private void addEventFragment() {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        AddEventsFragment addEventsFragment = new AddEventsFragment();
        transaction.replace(R.id.homeFrameLayout, addEventsFragment);
        transaction.addToBackStack("FragmentList");
        transaction.commit();
    }
}