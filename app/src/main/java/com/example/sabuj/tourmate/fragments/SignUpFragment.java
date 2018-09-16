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


public class SignUpFragment extends Fragment {
    EditText etFullName, etUserName, etPassword, etContactNo, etAddress;
    Button btnRegister;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialization(view);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initialization(View view) {
        etFullName = view.findViewById(R.id.etFullName);
        etUserName = view.findViewById(R.id.etUserName);
        etPassword = view.findViewById(R.id.etPassword);
        etContactNo = view.findViewById(R.id.etEmmergencyContactNo);
        etAddress = view.findViewById(R.id.etAddress);
        btnRegister = view.findViewById(R.id.btnRegister);
    }
}
