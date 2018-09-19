package com.example.sabuj.tourmate.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sabuj.tourmate.MainActivity;
import com.example.sabuj.tourmate.R;
import com.example.sabuj.tourmate.models.Common;
import com.example.sabuj.tourmate.models.User;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginFragment extends Fragment {
    EditText etLoginUserName, etLoginPassword;
    Button btnLoginCheck;
    FirebaseDatabase database;
    DatabaseReference table_user;
    ProgressDialog dialog;

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
        database = FirebaseDatabase.getInstance();
        table_user = database.getReference("User");

        btnLoginCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(getActivity());
                dialog.setMessage("Please Waiting......");
                dialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(etLoginUserName.getText().toString()).exists()) {

                            User user = dataSnapshot.child(etLoginUserName.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(etLoginPassword.getText().toString())) {
                                Toast.makeText(getActivity(), "Sign In Successfully ! ", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                Common.currentUser = user;
                                startActivity(intent);
                                getActivity().finish();
                            } else {
                                Toast.makeText(getActivity(), "Wrong Username or Password ! Please try again.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), "User Not Exist in Database !", Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                        }
                        dialog.dismiss();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getActivity(), databaseError.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void initialization(View view) {
        etLoginUserName = view.findViewById(R.id.etLoginUserName);
        etLoginPassword = view.findViewById(R.id.etLoginPassword);
        btnLoginCheck = view.findViewById(R.id.btnLoginCheck);

    }
}
