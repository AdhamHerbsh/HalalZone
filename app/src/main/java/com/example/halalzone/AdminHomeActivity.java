package com.example.halalzone;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdminHomeActivity extends AppCompatActivity {

    LinearLayout report, add, mng, video, warning,HalaItem;

    @SuppressLint("MissingInflatedId")
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

        warning = findViewById(R.id.warning);
        warning.setOnClickListener(this::towarning);

        video = findViewById(R.id.video);
        video.setOnClickListener(this::tovedio);

        HalaItem = findViewById(R.id.HalaItem);
        HalaItem.setOnClickListener(this::toHalaItem);

    }

    public void toHalaItem(View view) {
        Intent intent = new Intent(this, BarcodeScannerActivity.class);
        startActivity(intent);
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

    public void towarning(View view) {
        Intent intent = new Intent(this, AllWarningsActivity.class);
        startActivity(intent);
    }
    public void tovedio(View view) {
        Intent intent = new Intent(this, AddVideoActivity.class);
        startActivity(intent);
    }
}