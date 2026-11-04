package com.example.registrationform;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private EditText editTxtDateOfBirth;
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

        editTxtDateOfBirth = findViewById(R.id.edtTxtDateOfBirth);

        editTxtDateOfBirth.setOnClickListener(v -> showMaterialDatePicker());
    }

    private void showMaterialDatePicker() {
        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date of Birth")
                .setSelection(selectedDateInMillis != 0 ? selectedDateInMillis : MaterialDatePicker.todayInUtcMilliseconds())
                .build();

        datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                selectedDateInMillis = selection;
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                sdf.setTimeZone(TimeZone.getDefault());
                String formattedDate = sdf.format(new Date(selection));
                editTxtDateOfBirth.setText(formattedDate);
            }
        });

        datePicker.show(getSupportFragmentManager(), "DATE_PICKER");
    }

    public void onRegisterBtnClick(View view){
        @SuppressLint("WrongViewCast") EditText editTxtFirstname = findViewById(R.id.edtTxtFirstName);
        @SuppressLint("WrongViewCast") EditText editTxtDateOfBirth = findViewById(R.id.edtTxtDateOfBirth);
        @SuppressLint("WrongViewCast") EditText editTxtEmail = findViewById(R.id.edtTxtEmail);

        String firstName = editTxtFirstname.getText().toString().trim();
        String dateOfBirth = editTxtDateOfBirth.getText().toString().trim();
        String email = editTxtEmail.getText().toString().trim();

        if (firstName.isEmpty() || dateOfBirth.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        String message = "Registered:\n" + firstName + "\n" + dateOfBirth + "\n" + email;
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}