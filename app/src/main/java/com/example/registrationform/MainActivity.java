package com.example.registrationform;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private EditText editTxtDateOfBirth;
    private TextView txtDateOfBirth;
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
        txtDateOfBirth = findViewById(R.id.txtDateOfBirth);

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