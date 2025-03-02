package com.example.halalzone;

import android.database.Cursor;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.halalzone.databinding.ActivityMngBusinessBinding;

public class MngBusinessActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    ActivityMngBusinessBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMngBusinessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        loadData();

    }

    private void loadData() {
        Cursor cursor = databaseHelper.getBusiness();
        if (cursor != null) {
            BusinessesAdapter adapter = new BusinessesAdapter(this, cursor, 0);
            binding.listview.setAdapter(adapter);
        }
    }
}