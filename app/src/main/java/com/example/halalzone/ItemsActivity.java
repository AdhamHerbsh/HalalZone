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

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_items);

        // Set up Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        // Enable Back Navigation
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        gridView = findViewById(R.id.grid_view1);
        ArrayList<itemModel> list = new ArrayList<>();

        list.add(new itemModel("Cheese Burger","100 SAR", R.mipmap.ic_launcher_foreground));
        list.add(new itemModel("Cheese Burger","100 SAR", R.mipmap.ic_launcher_foreground));
        list.add(new itemModel("Cheese Burger","100 SAR", R.mipmap.ic_launcher_foreground));
        list.add(new itemModel("Cheese Burger","100 SAR", R.mipmap.ic_launcher_foreground));
        list.add(new itemModel("Cheese Burger","100 SAR", R.mipmap.ic_launcher_foreground));
        list.add(new itemModel("Cheese Burger","100 SAR", R.mipmap.ic_launcher_foreground));

        GridViewAdapter2 adapter = new GridViewAdapter2(this, list);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the clicked item
                itemModel selectedItem = list.get(position);

                // Show a toast (optional)
                Toast.makeText(ItemsActivity.this, "Clicked: " + selectedItem.getName(), Toast.LENGTH_SHORT).show();

                // Open a new activity and pass data
                Intent intent = new Intent(ItemsActivity.this, ItemActivity.class);
//                intent.putExtra("name", selectedItem.getName());
//                intent.putExtra("price", selectedItem.getNewprice();
//                intent.putExtra("image", selectedItem.getImage());
                startActivity(intent);
            }
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