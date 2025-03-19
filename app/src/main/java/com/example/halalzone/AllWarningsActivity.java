package com.example.halalzone;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AllWarningsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private WarningAdapter adapter;
    private DatabaseHelper databaseHelper;
    private Button btnAddWarning;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_warnings);

        recyclerView = findViewById(R.id.recyclerViewWarnings);
        btnAddWarning = findViewById(R.id.btnAddWarning);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseHelper = new DatabaseHelper(this);
        List<Warning> warnings = databaseHelper.getAllWarnings();

        adapter = new WarningAdapter(warnings);
        recyclerView.setAdapter(adapter);

        btnAddWarning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllWarningsActivity.this, AddWarningActivity.class));
            }
        });
    }
}
