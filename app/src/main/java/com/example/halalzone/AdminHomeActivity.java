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

public class AdminHomeActivity extends AppCompatActivity {

    Button report, add, mng, video, warning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        report = findViewById(R.id.report);
        report.setOnClickListener(this::toreport);

        add = findViewById(R.id.addd);
        add.setOnClickListener(this::toadd);

        mng = findViewById(R.id.mng);
        mng.setOnClickListener(this::tomng);

    }
    public void toreport(View view) {
        Intent intent = new Intent(this, ReportsActivity.class);
        startActivity(intent);
    }
    public void toadd(View view) {
        Intent intent = new Intent(this, AddBusinessActivity.class);
        startActivity(intent);
    }
    public void tomng(View view) {
        Intent intent = new Intent(this, MngBusinessActivity.class);
        startActivity(intent);
    }
}