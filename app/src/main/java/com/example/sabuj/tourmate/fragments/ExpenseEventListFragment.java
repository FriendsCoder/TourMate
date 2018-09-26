package com.example.sabuj.tourmate.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.sabuj.tourmate.R;
import com.example.sabuj.tourmate.adapters.EventListExpenseAdapter;
import com.example.sabuj.tourmate.models.Common;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExpenseEventListFragment extends Fragment {
    RecyclerView recyclerView;
    EventListExpenseAdapter eventListAdapter;
    ProgressBar progressBar;
    private DatabaseReference refEvent;
    private DatabaseReference refUserEvent;
    ArrayList<String> eventList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_eventlist_expense, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_expense_eventlist);
        progressBar = view.findViewById(R.id.spin_progress);
        refEvent = FirebaseDatabase.getInstance().getReference("Events");
        String user = Common.currentUser.getUserName();
        refUserEvent = refEvent.child(user);
        Wave wave = new Wave();
        progressBar.setIndeterminateDrawable(wave);
        progressBar.setVisibility(View.VISIBLE);
        eventList = new ArrayList<>();

        refUserEvent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                    String eventName=eventSnapshot.getKey();
                    eventList.add(eventName);
                }
                progressBar.setVisibility(View.INVISIBLE);
                if (eventList != null && eventList.size() > 0) {
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                    eventListAdapter = new EventListExpenseAdapter(getContext(), eventList,transaction);
                    recyclerView.setAdapter(eventListAdapter);
                    eventListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
