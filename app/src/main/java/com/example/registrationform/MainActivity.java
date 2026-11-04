package com.example.registrationform;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private long selectedDateInMillis = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = findViewById(R.id.calendarView);
        
        // Set listener for date selection
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectedDateInMillis = view.getDate();
        });
        
        // Set default to today
        selectedDateInMillis = System.currentTimeMillis();
    }

    public void onRegisterBtnClick(View view){
        @SuppressLint("WrongViewCast") EditText editTxtFirstname = findViewById(R.id.edtTxtFirstName);
        @SuppressLint("WrongViewCast") EditText editTxtEmail = findViewById(R.id.edtTxtEmail);

        String firstName = editTxtFirstname.getText().toString().trim();
        String email = editTxtEmail.getText().toString().trim();

        if (firstName.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Format the selected date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getDefault());
        String dateOfBirth = sdf.format(new Date(selectedDateInMillis));

        String message = "Registered:\n" + firstName + "\n" + dateOfBirth + "\n" + email;
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}