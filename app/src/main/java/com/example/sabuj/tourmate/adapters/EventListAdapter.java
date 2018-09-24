package com.example.sabuj.tourmate.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sabuj.tourmate.R;

import java.util.ArrayList;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventListViewHolder> {
    private Context context;
    private ArrayList<String> eventList;

    public EventListAdapter(Context context, ArrayList<String> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_event_list, parent, false);
        return new EventListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventListViewHolder holder, int position) {

        holder.tvEventList.setText(eventList.get(0));
        System.out.println(eventList.get(0));
        holder.llEventList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, eventList.get(0), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class EventListViewHolder extends RecyclerView.ViewHolder {
        TextView tvEventList;
        LinearLayout llEventList;

        public EventListViewHolder(View itemView) {
            super(itemView);
            tvEventList = itemView.findViewById(R.id.tvEventList);
            llEventList=itemView.findViewById(R.id.llEventList);
        }
    }
}
