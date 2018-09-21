package com.example.sabuj.tourmate.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sabuj.tourmate.R;
import com.example.sabuj.tourmate.models.Event;
import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {
    private ArrayList<Event> getEventsList;
   private   Context context;

    public EventsAdapter(Context context, ArrayList<Event> getEventsList) {
        this.getEventsList = getEventsList;
        this.context = context;
    }

    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_event, parent, false);
        EventsViewHolder eventsViewHolder = new EventsViewHolder(view);
        return eventsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventsViewHolder holder, int position) {
        Event event = getEventsList.get(position);
        holder.tvDestination.setText(event.getDestination());
        holder.tvFromDate.setText(event.getFromDate());
        holder.tvToDate.setText(event.getToDate());
        holder.tvEstimationBudget.setText(event.getBudget());
    }

    @Override
    public int getItemCount() {
        return getEventsList.size();
    }

    public class EventsViewHolder extends RecyclerView.ViewHolder {
         TextView tvDestination, tvFromDate, tvToDate, tvEstimationBudget;
        public LinearLayout llitemEvent;

         EventsViewHolder(View itemView) {
            super(itemView);
            tvDestination = itemView.findViewById(R.id.tvEventDestination);
            tvFromDate = itemView.findViewById(R.id.tvFromDate);
            tvToDate = itemView.findViewById(R.id.tvToDate);
            tvEstimationBudget = itemView.findViewById(R.id.tvBudgets);
            llitemEvent = itemView.findViewById(R.id.llitemEvent);
        }
    }
}
