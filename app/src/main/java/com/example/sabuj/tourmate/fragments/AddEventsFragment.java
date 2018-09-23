package com.example.sabuj.tourmate.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sabuj.tourmate.R;
import com.example.sabuj.tourmate.models.Common;
import com.example.sabuj.tourmate.models.Event;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class AddEventsFragment extends Fragment {
    private EditText etDestination, etBudget;
    private Button btnFromDate, btnToDate, btnEventSave;
    private String  fromDate, toDate;
    private DatabaseReference refEvent;
    private DatabaseReference refUserEvent;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_event, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialization(view);

        refEvent = FirebaseDatabase.getInstance().getReference("Events");

        String user = Common.currentUser.getUserName();
        refUserEvent=refEvent.child(user);

        btnFromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        fromDate = dayOfMonth + "-" + month + "-" + year;
                        btnFromDate.setText(fromDate);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();

            }
        });
        btnToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        toDate = dayOfMonth + "-" + month + "-" + year;
                        btnToDate.setText(toDate);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();

            }
        });
        btnEventSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                refUserEvent.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Event event = new Event(etDestination.getText().toString(), etBudget.getText().toString(), fromDate, toDate);
                        refUserEvent.child(etDestination.getText().toString()).setValue(event);
                        Toast.makeText(getActivity(), "Successfully saved event.", Toast.LENGTH_SHORT).show();
                        clearData();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    private void clearData() {
        etDestination.setText("");
        etBudget.setText("");
        btnFromDate.setText("From Date");
        btnToDate.setText("To Date");
    }


    private void initialization(View view) {
        etDestination = view.findViewById(R.id.etDestination);
        etBudget = view.findViewById(R.id.etEstimationBudget);
        btnFromDate = view.findViewById(R.id.btnFromDate);
        btnToDate = view.findViewById(R.id.btnToDate);
        btnEventSave = view.findViewById(R.id.btnEventSave);
    }
}
