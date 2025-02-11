package com.example.halalzone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GateActivity extends AppCompatActivity {

    Button _LOGIN_BTN, _CNA_BTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gate);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        _CNA_BTN = findViewById(R.id._CNA_BTN);

        _LOGIN_BTN = findViewById(R.id._LOGIN_BTN);

        _CNA_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRegister(v);
            }
        });
        _LOGIN_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLogin(v);
            }
        });


    }

    public void toLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void toRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


}