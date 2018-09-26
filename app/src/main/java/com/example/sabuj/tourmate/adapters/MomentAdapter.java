package com.example.sabuj.tourmate.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sabuj.tourmate.R;
import com.example.sabuj.tourmate.models.Moment;

import java.util.ArrayList;

public class MomentAdapter extends RecyclerView.Adapter<MomentAdapter.MomentViewHolder> {
    private ArrayList<Moment> getMomentsList;
    private Context context;

    public MomentAdapter(Context context, ArrayList<Moment> getMomentsList) {
        this.getMomentsList = getMomentsList;
        this.context = context;
    }

    @NonNull
    @Override
    public MomentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_moment, parent, false);
        return new MomentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MomentViewHolder holder, int position) {
        Moment moment = getMomentsList.get(position);
        holder.tvCurrentDate.setText(moment.getMomentDate());
        holder.tvCurrentTime.setText(moment.getMomentTime());
        holder.tvMomentDetails.setText(moment.getMomentDetails());
        Bitmap momentPhoto=decodeBase64(moment.getMomentPhoto());
        holder.ivMomentPhoto.setImageBitmap(momentPhoto);
    }

    @Override
    public int getItemCount() {
        return getMomentsList.size();
    }

    public class MomentViewHolder extends RecyclerView.ViewHolder {
        TextView tvCurrentDate, tvCurrentTime, tvMomentDetails;
        ImageView ivMomentPhoto;

        public MomentViewHolder(View itemView) {
            super(itemView);
            tvCurrentDate = itemView.findViewById(R.id.tvCurrentDate);
            tvCurrentTime = itemView.findViewById(R.id.tvCurrrentTime);
            tvMomentDetails = itemView.findViewById(R.id.tvMomentDetails);
            ivMomentPhoto = itemView.findViewById(R.id.ivMomentPhoto);
        }
    }
    public static Bitmap decodeBase64(String input) {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}
