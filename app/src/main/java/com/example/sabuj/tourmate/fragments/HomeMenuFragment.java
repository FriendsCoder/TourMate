package com.example.sabuj.tourmate.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.sabuj.tourmate.R;

public class HomeMenuFragment extends Fragment {
    ImageButton ibHomeMoments, ibHomeEvents, ibHomeExpenses, ibHomeNearby, ibHomeWeather;
    FragmentTransaction transaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialization(view);
        ibHomeMoments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = getActivity().getSupportFragmentManager().beginTransaction();
                getMomentFragment(transaction);
            }
        });

        ibHomeEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = getActivity().getSupportFragmentManager().beginTransaction();
                getEventsFragment(transaction);
            }
        });
        ibHomeExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = getActivity().getSupportFragmentManager().beginTransaction();
                getExpensesFragment(transaction);
            }
        });

        ibHomeNearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = getActivity().getSupportFragmentManager().beginTransaction();
                getNearbyFragment(transaction);
            }
        });

        ibHomeWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction = getActivity().getSupportFragmentManager().beginTransaction();
                getWeatherFragment(transaction);
            }
        });
    }

    public void getWeatherFragment(FragmentTransaction transaction) {
        WeatherFragment weatherFragment = new WeatherFragment();
        transaction.replace(R.id.homeFrameLayout, weatherFragment);
        transaction.addToBackStack("FragmentList");
        transaction.commit();
    }

    public void getNearbyFragment(FragmentTransaction transaction) {
        NearbyFragment nearbyFragment = new NearbyFragment();
        transaction.replace(R.id.homeFrameLayout, nearbyFragment);
        transaction.addToBackStack("FragmentList");
        transaction.commit();
    }

    public void getExpensesFragment(FragmentTransaction transaction) {
        ExpenseEventListFragment expenseEventListFragment = new ExpenseEventListFragment();
        transaction.replace(R.id.homeFrameLayout, expenseEventListFragment);
        transaction.addToBackStack("FragmentList");
        transaction.commit();

    }

    public void getEventsFragment(FragmentTransaction transaction) {
        EventsFragment eventsFragment = new EventsFragment();
        transaction.replace(R.id.homeFrameLayout, eventsFragment);
        transaction.addToBackStack("FragmentList");
        transaction.commit();
    }

    public void getMomentFragment(FragmentTransaction transaction) {
        MomentEventListFragment momentEventListFragment = new MomentEventListFragment();
        transaction.replace(R.id.homeFrameLayout, momentEventListFragment);
        transaction.addToBackStack("FragmentList");
        transaction.commit();
    }

    private void initialization(View view) {
        ibHomeMoments = view.findViewById(R.id.ibHomeMoments);
        ibHomeEvents = view.findViewById(R.id.ibHomeEvents);
        ibHomeExpenses = view.findViewById(R.id.ibHomeExpenses);
        ibHomeNearby = view.findViewById(R.id.ibHomeNearby);
        ibHomeWeather = view.findViewById(R.id.ibHomeWeather);
    }


}
