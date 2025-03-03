package com.example.halalzone;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class AdminLoginActivity extends AppCompatActivity {

    Button LOG_LOGIN_BTN;
    EditText emailInput, passwordInput;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dbHelper = new DatabaseHelper(this);

        LOG_LOGIN_BTN = findViewById(R.id.LOG_LOGIN_BTN);
        emailInput = findViewById(R.id._EMAIL_INPUT);
        passwordInput = findViewById(R.id._PASSWORD_INPUT);

        LOG_LOGIN_BTN.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();
            if(dbHelper.checkadmin(email,password)){
               Intent intent = new Intent(this, AdminHomeActivity.class);
               startActivity(intent);

           }else{
                Toast.makeText(this, "Invalid Info", Toast.LENGTH_SHORT).show();
            }

        });
    }
}