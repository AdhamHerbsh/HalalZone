package com.example.halalzone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.halalzone.databinding.ActivityBusniessLoginBinding;
import com.example.halalzone.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    Button LOG_SIGNUP_BTN;
    Button LOG_LOGIN_BTN;
    EditText emailInput, passwordInput;

    DatabaseHelper dbHelper;
    SharedPreferences sharedPref;
    ActivityLoginBinding binding;

    public void saveUserMail(String email) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("User", email);
        editor.apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dbHelper = new DatabaseHelper(this);
        sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        LOG_SIGNUP_BTN = findViewById(R.id.LOG_SIGNUP_BTN);
        LOG_LOGIN_BTN = findViewById(R.id.LOG_LOGIN_BTN);
        emailInput = findViewById(R.id._EMAIL_INPUT);
        passwordInput = findViewById(R.id._PASSWORD_INPUT);

        LOG_LOGIN_BTN.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();
            if(dbHelper.checkuser(email,password)){
                saveUserMail(email);
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);

            }else{
                Toast.makeText(this, "Invalid Info", Toast.LENGTH_SHORT).show();
            }

        });

        LOG_SIGNUP_BTN.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);

        });


    }
}