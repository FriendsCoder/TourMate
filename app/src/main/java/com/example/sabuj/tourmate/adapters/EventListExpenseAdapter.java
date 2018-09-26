package com.example.sabuj.tourmate.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sabuj.tourmate.R;
import com.example.sabuj.tourmate.fragments.ExpensesFragment;
import com.example.sabuj.tourmate.models.Common;

import java.util.ArrayList;

public class EventListExpenseAdapter extends RecyclerView.Adapter<EventListExpenseAdapter.EventListViewHolder> {
    private Context context;
    private ArrayList<String> eventListExpense;
    FragmentTransaction transaction;

    public EventListExpenseAdapter(Context context, ArrayList<String> eventListExpense, FragmentTransaction transaction) {
        this.context = context;
        this.eventListExpense = eventListExpense;
        this.transaction=transaction;
    }

    @NonNull
    @Override
    public EventListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_event_list, parent, false);
        return new EventListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventListViewHolder holder, final int position) {
        final String eventName = eventListExpense.get(position);
        holder.tvEventList.setText(eventName);

        holder.llEventList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Common.currentEventName = eventName;
                getExpensesFragment(transaction);
            }
        });
    }

    private void getExpensesFragment(FragmentTransaction transaction) {
        ExpensesFragment expensesFragment = new ExpensesFragment();
        transaction.replace(R.id.homeFrameLayout, expensesFragment);
        transaction.addToBackStack("FragmentList");
        transaction.commit();
    }

    @Override
    public int getItemCount() {
        return eventListExpense.size();
    }

    public class EventListViewHolder extends RecyclerView.ViewHolder {
        TextView tvEventList;
        LinearLayout llEventList;

        public EventListViewHolder(View itemView) {
            super(itemView);
            tvEventList = itemView.findViewById(R.id.tvEventList);
            llEventList = itemView.findViewById(R.id.llEventList);
        }
    }
}
