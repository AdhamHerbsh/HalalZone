package com.example.halalzone;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.halalzone.databinding.ActivityReportsBinding;

public class ReportsActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    ActivityReportsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReportsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this);

        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loadData();
    }

    private void loadData() {
        Cursor cursor = databaseHelper.getProblems();
        if (cursor != null) {
            ProblemsAdapter adapter = new ProblemsAdapter(this, cursor, 0);
            binding.listview.setAdapter(adapter);
        }
    }
}
