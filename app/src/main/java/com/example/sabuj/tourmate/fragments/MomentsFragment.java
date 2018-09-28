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
import com.example.sabuj.tourmate.adapters.MomentAdapter;
import com.example.sabuj.tourmate.models.Common;
import com.example.sabuj.tourmate.models.Moment;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MomentsFragment extends Fragment {
    private DatabaseReference refMoment;
    private DatabaseReference refUserMoment;
    private DatabaseReference refUserEventMoment;
    ArrayList<Moment> momentList;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    MomentAdapter momentAdapter;
    private FloatingActionButton fabMoment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_moments, container, false);
        initialization(view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refMoment = FirebaseDatabase.getInstance().getReference("Moments");
        String user = Common.currentUser.getUserName();
        String userMoment = Common.currentEventName;
        refUserMoment = refMoment.child(user);
        refUserEventMoment = refUserMoment.child(userMoment);

        Wave wave = new Wave();
        progressBar.setIndeterminateDrawable(wave);
        progressBar.setVisibility(View.VISIBLE);
        momentList = new ArrayList<>();

        refUserEventMoment.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataMoment : dataSnapshot.getChildren()) {
                    Moment moment = dataMoment.getValue(Moment.class);
                    momentList.add(moment);
                }
                progressBar.setVisibility(View.INVISIBLE);
                if (momentList != null && momentList.size() > 0) {
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    momentAdapter = new MomentAdapter(getActivity(), momentList);
                    recyclerView.setAdapter(momentAdapter);
                    momentAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        fabMoment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMomentFragment();
            }
        });
    }

    private void initialization(View view) {
        fabMoment = view.findViewById(R.id.fab_moments);
        recyclerView = view.findViewById(R.id.rv_moments);
        progressBar = view.findViewById(R.id.spin_moments);
    }

    private void addMomentFragment() {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        AddMomentFragment addMomentFragment = new AddMomentFragment();
        transaction.replace(R.id.homeFrameLayout, addMomentFragment);
        transaction.addToBackStack("FragmentList");
        transaction.commit();
    }
}