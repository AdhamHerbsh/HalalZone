package com.example.halalzone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {

    Button CNA_SIGNUP_BTN;
    Button CNA_LOGIN_BTN;
    EditText _NAME_INPUT;
    EditText _PHONE_INPUT;
    EditText _EMAIL_INPUT;
    EditText _PASSWORD_INPUT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CNA_LOGIN_BTN = findViewById(R.id.CNA_LOGIN_BTN);
        CNA_SIGNUP_BTN = findViewById(R.id.CNA_SIGNUP_BTN);

        _NAME_INPUT = findViewById(R.id._NAME_INPUT);
        _PHONE_INPUT = findViewById(R.id._PHONE_INPUT);
        _EMAIL_INPUT = findViewById(R.id._EMAIL_INPUT);
        _PASSWORD_INPUT = findViewById(R.id._PASSWORD_INPUT);


        CNA_LOGIN_BTN.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        CNA_SIGNUP_BTN.setOnClickListener(v -> {
            Toast.makeText(this, "This Process Not Available At The Moment", Toast.LENGTH_SHORT).show();
        });
    }
}