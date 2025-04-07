package com.example.halalzone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {

    Button CNA_SIGNUP_BTN;
    Button CNA_LOGIN_BTN;
    EditText _NAME_INPUT, _PHONE_INPUT, _EMAIL_INPUT, _PASSWORD_INPUT, _Address_INPUT;
    SharedPreferences sharedPref;
    RadioGroup radioGroupBusinessCountry, radioGroupGender;

    private DatabaseHelper databaseHelper;

    public void saveUserMail(String email) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("User", email);
        editor.apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        databaseHelper = new DatabaseHelper(this);
        sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);

        // Ensure main layout exists before using findViewById
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Views
        CNA_LOGIN_BTN = findViewById(R.id.CNA_LOGIN_BTN);
        CNA_SIGNUP_BTN = findViewById(R.id.CNA_SIGNUP_BTN);

        _NAME_INPUT = findViewById(R.id._NAME_INPUT);
        _PHONE_INPUT = findViewById(R.id._PHONE_INPUT);
        _EMAIL_INPUT = findViewById(R.id._EMAIL_INPUT);
        _Address_INPUT = findViewById(R.id._Address_INPUT);
        _PASSWORD_INPUT = findViewById(R.id._PASSWORD_INPUT);

        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioGroupBusinessCountry = findViewById(R.id.radioGroupBusinessCountry);

        Spinner countryCodeSpinner = findViewById(R.id.country_code_spinner);
        ArrayAdapter<String> countryCodeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                new String[]{"+33", "+1"});
        countryCodeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countryCodeSpinner.setAdapter(countryCodeAdapter);

        CNA_LOGIN_BTN.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        CNA_SIGNUP_BTN.setOnClickListener(v -> {
            String name = _NAME_INPUT.getText().toString().trim();
            String phone = countryCodeSpinner.getSelectedItem().toString() + _PHONE_INPUT.getText().toString().trim();
            String email = _EMAIL_INPUT.getText().toString().trim();
            String password = _PASSWORD_INPUT.getText().toString().trim();
            String address = _Address_INPUT.getText().toString().trim();

            // Ensure RadioGroups are not null before using getCheckedRadioButtonId()
            if (radioGroupGender == null || radioGroupBusinessCountry == null) {
                Toast.makeText(this, "Error loading form. Try again.", Toast.LENGTH_SHORT).show();
                return;
            }

            int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
            int selectedCountryId = radioGroupBusinessCountry.getCheckedRadioButtonId();

            // Ensure a selection is made
            if (selectedGenderId == -1 || selectedCountryId == -1) {
                Toast.makeText(this, "Please select gender and business country", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selectedGender = findViewById(selectedGenderId);
            RadioButton selectedCountry = findViewById(selectedCountryId);

            String gender = selectedGender.getText().toString();
            String Country = selectedCountry.getText().toString();

            // Validate inputs
            if (!email.isEmpty() && !name.isEmpty() && !phone.isEmpty() && !password.isEmpty()) {

                if (password.length() < 8) {
                    Toast.makeText(this, "Password must be at least 8 characters long", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (phone.length() > 11) {
                    Toast.makeText(this, "Phone number must be less than 11 digits", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean isInserted = databaseHelper.adduser(email, name, password, phone, address, Country, gender);
                if (isInserted) {
                    saveUserMail(email);
                    Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
