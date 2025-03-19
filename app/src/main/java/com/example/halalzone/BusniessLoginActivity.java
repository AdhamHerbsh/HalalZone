package com.example.halalzone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class BusniessLoginActivity extends AppCompatActivity {

    Button LOG_LOGIN_BTN, LOG_SIGNUP_BTN;
    EditText emailInput, passwordInput;
    DatabaseHelper dbHelper;
    SharedPreferences sharedPref;


    public void saveBusiness(String username) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("Business", username);
        editor.apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_busniess_login);
        dbHelper = new DatabaseHelper(this);
        sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);


        LOG_LOGIN_BTN = findViewById(R.id.LOG_LOGIN_BTN);
        emailInput = findViewById(R.id._EMAIL_INPUT);
        passwordInput = findViewById(R.id._PASSWORD_INPUT);
        LOG_SIGNUP_BTN = findViewById(R.id.LOG_SIGNUP_BTN);
        LOG_LOGIN_BTN.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();
            if(dbHelper.checkbusiness(email,password)){
                saveBusiness(email);
                Intent intent = new Intent(this, BusinessHomeActivity.class);
                startActivity(intent);

            }else{
                Toast.makeText(this, "Invalid Info", Toast.LENGTH_SHORT).show();
            }

        });

        LOG_SIGNUP_BTN.setOnClickListener(v -> {
            Intent intent = new Intent(this, BusinessSignUpActivity.class);
            startActivity(intent);

        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}