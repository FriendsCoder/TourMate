package com.example.sabuj.tourmate.fragments;

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
    ImageButton ibHomeMoments, ibHomeBudgets, ibHomeExpenses, ibHomeNearby, ibHomeWeather;
    FragmentTransaction transaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_menu_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialization(view);
        ibHomeMoments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMomentFragment();
            }
        });

        ibHomeBudgets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BudgetsFragment budgetsFragment = new BudgetsFragment();
                transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.homeFrameLayout, budgetsFragment);
                transaction.commit();
            }
        });
        ibHomeExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExpensesFragment expensesFragment = new ExpensesFragment();
                transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.homeFrameLayout, expensesFragment);
                transaction.commit();
            }
        });

        ibHomeNearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NearbyFragment nearbyFragment = new NearbyFragment();
                transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.homeFrameLayout, nearbyFragment);
                transaction.commit();
            }
        });

        ibHomeWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeatherFragment weatherFragment = new WeatherFragment();
                transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.homeFrameLayout, weatherFragment);
                transaction.commit();
            }
        });
    }

    public void getMomentFragment() {
        MomentsFragment momentsFragment = new MomentsFragment();
        transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.homeFrameLayout, momentsFragment);
        transaction.commit();
    }

    private void initialization(View view) {
        ibHomeMoments = view.findViewById(R.id.ibHomeMoments);
        ibHomeBudgets = view.findViewById(R.id.ibHomeBudgets);
        ibHomeExpenses = view.findViewById(R.id.ibHomeExpenses);
        ibHomeNearby = view.findViewById(R.id.ibHomeNearby);
        ibHomeWeather = view.findViewById(R.id.ibHomeWeather);
    }


}
