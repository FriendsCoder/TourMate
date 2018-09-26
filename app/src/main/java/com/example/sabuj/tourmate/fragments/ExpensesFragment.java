package com.example.sabuj.tourmate.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.sabuj.tourmate.R;
import com.example.sabuj.tourmate.adapters.ExpenseAdapter;
import com.example.sabuj.tourmate.models.Common;


import com.example.sabuj.tourmate.models.Expense;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExpensesFragment extends Fragment {
    private DatabaseReference refExpense;
    private DatabaseReference refUserExpense;
    private DatabaseReference refUserEventExpense;
    ArrayList<Expense> expenseList;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    private FloatingActionButton fabExpense;
    ExpenseAdapter expenseAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expenses, container, false);
        initialization(view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refExpense = FirebaseDatabase.getInstance().getReference("Expenses");
        String user = Common.currentUser.getUserName();
        String userEvent=Common.currentEventName;
        refUserExpense = refExpense.child(user);
        refUserEventExpense=refUserExpense.child(userEvent);

        Wave wave = new Wave();
        progressBar.setIndeterminateDrawable(wave);
        progressBar.setVisibility(View.VISIBLE);
        expenseList = new ArrayList<>();

        refUserEventExpense.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.INVISIBLE);
                for (DataSnapshot dataExpense : dataSnapshot.getChildren()) {
                    Expense expense = dataExpense.getValue(Expense.class);
                    expenseList.add(expense);
                }
                if (expenseList != null && expenseList.size() > 0) {
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    expenseAdapter = new ExpenseAdapter(getContext(), expenseList);
                    recyclerView.setAdapter(expenseAdapter);
                    expenseAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        fabExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpenseFragment();
            }
        });
    }

    private void addExpenseFragment() {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        AddExpenseFragment addExpenseFragment = new AddExpenseFragment();
        transaction.replace(R.id.homeFrameLayout, addExpenseFragment);
        transaction.addToBackStack("FragmentList");
        transaction.commit();
    }

    private void initialization(View view) {
        fabExpense = view.findViewById(R.id.fab_expenses);
        recyclerView = view.findViewById(R.id.rv_expenses);
        progressBar = view.findViewById(R.id.spin_expenses);
    }
}