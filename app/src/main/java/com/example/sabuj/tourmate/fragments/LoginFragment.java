package com.example.sabuj.tourmate.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.sabuj.tourmate.R;


public class LoginFragment extends Fragment {
    EditText etLoginUserName, etLoginPassword;
    Button btnLoginCheck;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialization(view);
        btnLoginCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initialization(View view) {
        etLoginUserName = view.findViewById(R.id.etLoginUserName);
        etLoginPassword = view.findViewById(R.id.etLoginPassword);
        btnLoginCheck = view.findViewById(R.id.btnLoginCheck);

    }
}
