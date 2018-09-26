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

import com.example.sabuj.tourmate.R;
import com.example.sabuj.tourmate.adapters.MomentAdapter;
import com.example.sabuj.tourmate.models.Common;
import com.example.sabuj.tourmate.models.Moment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MomentsFragment extends Fragment {
    private DatabaseReference refEvent;
    private DatabaseReference refUserEvent;
    ArrayList<Moment> eventList;
    RecyclerView recyclerView;
    MomentAdapter momentAdapter;
    FragmentTransaction transaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_moments, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refEvent = FirebaseDatabase.getInstance().getReference("Events");
        String user = Common.currentUser.getUserName();
        refUserEvent = refEvent.child(user);
        recyclerView = view.findViewById(R.id.rvEventlistofMoment);
        eventList = new ArrayList<>();
        refUserEvent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataEventName : dataSnapshot.getChildren()) {
                    String eventName = dataEventName.getKey();
                   // eventList.add(eventName);
                }
                if (eventList != null && eventList.size() > 0) {
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    momentAdapter = new MomentAdapter(getActivity(), eventList);
//                    recyclerView.setAdapter(eventListMomentAdapter);
//                    eventListMomentAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}