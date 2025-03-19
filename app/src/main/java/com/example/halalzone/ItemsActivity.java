package com.example.halalzone;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ItemsActivity extends AppCompatActivity {
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_items);

        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        gridView = findViewById(R.id.grid_view1);

        // Get the business ID from the Intent
        String businessId = getIntent().getStringExtra("business_email");

        // Load items from the database
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        ArrayList<itemModel> list = dbHelper.getItemsByBusinessmail(businessId);

        GridViewAdapter2 adapter = new GridViewAdapter2(this, list);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            // Get the clicked item
            itemModel selectedItem = list.get(position);

            // Show a toast (optional)
            Toast.makeText(ItemsActivity.this, "Clicked: " + selectedItem.getName(), Toast.LENGTH_SHORT).show();

            // Open a new activity and pass the item_id only
            Intent intent = new Intent(ItemsActivity.this, ItermActivity.class);
            intent.putExtra("ITEM_ID", selectedItem.getId()); // Pass only the item_id
            startActivity(intent);
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}