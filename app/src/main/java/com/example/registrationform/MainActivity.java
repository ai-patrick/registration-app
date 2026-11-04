package com.example.registrationform;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText editTxtDateOfBirth;
    private TextView txtDateOfBirth;

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

        editTxtDateOfBirth = findViewById(R.id.edtTxtDateOfBirth);
        txtDateOfBirth = findViewById(R.id.txtDateOfBirth);

        editTxtDateOfBirth.setOnClickListener(v -> showDatePickerDialog());
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String selectedDate = String.format(Locale.getDefault(), "%02d/%02d/%d", selectedDay, selectedMonth + 1, selectedYear);
                    editTxtDateOfBirth.setText(selectedDate);
                },
                year, month, day);

        datePickerDialog.show();
    }

    public void onRegisterBtnClick(View view){
        TextView txtFirstName = findViewById(R.id.txtFirstName);
        TextView txtSecondName = findViewById(R.id.txtSecondName);
        TextView txtEmail = findViewById(R.id.txtEmail);

        @SuppressLint("WrongViewCast") EditText editTxtFirstname = findViewById(R.id.edtTxtFirstName);
        @SuppressLint("WrongViewCast") EditText editTxtSecondName = findViewById(R.id.edtTxtSecondName);
        @SuppressLint("WrongViewCast") EditText editTxtEmail = findViewById(R.id.edtTxtEmail);
        @SuppressLint("WrongViewCast") EditText editTxtDateOfBirth = findViewById(R.id.edtTxtDateOfBirth);

        txtFirstName.setText("First Name: " + editTxtFirstname.getText().toString());
        txtSecondName.setText("Last Name: " + editTxtSecondName.getText().toString());
        txtEmail.setText("Email: " + editTxtEmail.getText().toString());
        txtDateOfBirth.setText("Date of Birth: " + editTxtDateOfBirth.getText().toString());
    }
}