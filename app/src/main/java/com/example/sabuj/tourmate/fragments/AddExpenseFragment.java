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
import android.widget.Toast;

import com.example.sabuj.tourmate.R;
import com.example.sabuj.tourmate.models.Common;
import com.example.sabuj.tourmate.models.Expense;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddExpenseFragment extends Fragment {
    private EditText etExpenseDetails, etExpenseAmount;
    private String currentDate, currentTime;
    private Button btnEntryRecord;
    private DatabaseReference refExpense;
    private DatabaseReference refUserExpense;
    private DatabaseReference refUserEventExpense;
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat timeFormat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_expense, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialization(view);
        refExpense = FirebaseDatabase.getInstance().getReference("Expenses");
        String user = Common.currentUser.getUserName();
        String userEvent=Common.currentEventName;
        refUserExpense = refExpense.child(user);
        refUserEventExpense=refUserExpense.child(userEvent);
        dateFormat = new SimpleDateFormat("d MMM");
        timeFormat = new SimpleDateFormat("h:mm a");
        btnEntryRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String details = etExpenseDetails.getText().toString();
                final String amount = etExpenseAmount.getText().toString();
                refUserEventExpense.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        currentDate = dateFormat.format(Calendar.getInstance().getTime());
                        currentTime = timeFormat.format(Calendar.getInstance().getTime());

                        Expense expense = new Expense(currentDate, currentTime, details, amount);
                        refUserEventExpense.child(details).setValue(expense);
                        Toast.makeText(getActivity(), "Successfully saved item.", Toast.LENGTH_SHORT).show();
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
        etExpenseDetails.setText("");
        etExpenseAmount.setText("");
    }

    private void initialization(View view) {
        etExpenseDetails = view.findViewById(R.id.etExpenseDetails);
        etExpenseAmount = view.findViewById(R.id.etExpenseAmount);
        btnEntryRecord = view.findViewById(R.id.btnEntryRecord);
    }
}
