package com.example.sabuj.tourmate.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sabuj.tourmate.R;
import com.example.sabuj.tourmate.models.Expense;
import com.example.sabuj.tourmate.models.Moment;

import java.util.ArrayList;

public class MomentAdapter extends RecyclerView.Adapter<MomentAdapter.ExpenseViewHolder> {
    private ArrayList<Moment> getExpensesList;
    private Context context;

    public MomentAdapter(Context context, ArrayList<Moment> getExpensesList) {
        this.getExpensesList = getExpensesList;
        this.context = context;
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_expense, parent, false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
//        Expense expense = getExpensesList.get(position);
//        holder.tvCurrentDate.setText(expense.getCurrentDate());
//        holder.tvCurrentTime.setText(expense.getCurrentTime());
//        holder.tvItem.setText(expense.getItem());
//        holder.tvPrice.setText(expense.getPrice());
    }

    @Override
    public int getItemCount() {
        return getExpensesList.size();
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder {
        TextView tvCurrentDate, tvCurrentTime, tvItem, tvPrice;

        public ExpenseViewHolder(View itemView) {
            super(itemView);
            tvCurrentDate = itemView.findViewById(R.id.tvCurrentDate);
            tvCurrentTime = itemView.findViewById(R.id.tvCurrrentTime);
            tvItem = itemView.findViewById(R.id.tvItem);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}
