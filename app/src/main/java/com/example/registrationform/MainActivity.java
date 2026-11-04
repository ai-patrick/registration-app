package com.example.registrationform;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private EditText editTxtDateDisplay;
    private long selectedDateInMillis = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        calendarView = findViewById(R.id.calendarView);
        
        // Add a hidden EditText to display the selected date
        editTxtDateDisplay = new EditText(this);
        editTxtDateDisplay.setVisibility(View.GONE);
        
        // Set listener for date selection
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectedDateInMillis = view.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            sdf.setTimeZone(TimeZone.getDefault());
            String formattedDate = sdf.format(new Date(selectedDateInMillis));
            // You could show this in a TextView if needed
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