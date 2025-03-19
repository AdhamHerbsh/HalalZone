package com.example.halalzone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BusinessWarningsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private WarningAdapter adapter;
    private List<Warning> warningList;
    private DatabaseHelper databaseHelper;
    private TextView txtBusinessEmail;
    SharedPreferences sharedPref;
    public String getUserName() {
        return sharedPref.getString("Business", "No Name");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_warnings);
        sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);

        recyclerView = findViewById(R.id.recyclerViewBusinessWarnings);
        txtBusinessEmail = findViewById(R.id.txtBusinessEmail);
        databaseHelper = new DatabaseHelper(this);
        warningList = new ArrayList<>();

        String businessEmail = getUserName();

        if (businessEmail != null) {
            txtBusinessEmail.setText("Warnings for: " + businessEmail);
            loadWarnings(businessEmail);
        } else {
            Toast.makeText(this, "No email provided", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void loadWarnings(String email) {
        warningList = databaseHelper.getWarningsByEmail(email); // Get list directly

        adapter = new WarningAdapter(warningList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

}
