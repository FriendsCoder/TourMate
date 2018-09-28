package com.example.sabuj.tourmate.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sabuj.tourmate.R;
import com.example.sabuj.tourmate.models.Common;
import com.example.sabuj.tourmate.models.Moment;

public class MomentDetailsFragment extends Fragment {
    TextView tvDeCurrentDate, tvDeCurrrentTime, tvDeMomentDetails;
    ImageView ivDeMomentPhoto;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_moment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialization(view);
        //        Moment moment= Common.momentDetails;
        tvDeCurrentDate.setText(Common.momentDetails.getMomentDate());
        tvDeCurrrentTime.setText(Common.momentDetails.getMomentTime());
        tvDeMomentDetails.setText(Common.momentDetails.getMomentDetails());
        Bitmap momentPhoto = decodeBase64(Common.momentDetails.getMomentPhoto());
        ivDeMomentPhoto.setImageBitmap(momentPhoto);
    }

    public static Bitmap decodeBase64(String input) {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    private void initialization(View view) {
        tvDeCurrentDate = view.findViewById(R.id.tvDeCurrentDate);
        tvDeCurrrentTime = view.findViewById(R.id.tvDeCurrrentTime);
        tvDeMomentDetails = view.findViewById(R.id.tvDeMomentDetails);
        ivDeMomentPhoto = view.findViewById(R.id.ivDeMomentPhoto);

    }
}
