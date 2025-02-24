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

    Button USER_BTN, BUS_BTN, ADMIN_BTN;

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

        USER_BTN = findViewById(R.id.USER_BTN);
        BUS_BTN = findViewById(R.id.BUS_BTN);
        ADMIN_BTN = findViewById(R.id.ADMIN_BTN);

        USER_BTN.setOnClickListener(this::touser);
        BUS_BTN.setOnClickListener(this::tobus);
        BUS_BTN.setOnClickListener(this::toadmin);


    }

    public void touser(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void tobus(View view) {
//        Intent intent = new Intent(this, .class);
//        startActivity(intent);
    }
    public void toadmin(View view) {
//        Intent intent = new Intent(this, .class);
//        startActivity(intent);
    }


}