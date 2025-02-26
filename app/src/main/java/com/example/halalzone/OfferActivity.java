package com.example.halalzone;

import android.os.Bundle;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class OfferActivity extends AppCompatActivity {

    private GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_offer);


        // Set up Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable Back Navigation
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        gridView = findViewById(R.id.grid_view);
        ArrayList<OfferModel> list = new ArrayList<>();

        list.add(new OfferModel("Cheese Burger","The Burgers","150","100 SAR", R.mipmap.ic_launcher_foreground));
        list.add(new OfferModel("Cheese Burger","The Burgers","150","100 SAR", R.mipmap.ic_launcher_foreground));
        list.add(new OfferModel("Cheese Burger","The Burgers","150","100 SAR", R.mipmap.ic_launcher_foreground));
        list.add(new OfferModel("Cheese Burger","The Burgers","150","100 SAR", R.mipmap.ic_launcher_foreground));
        list.add(new OfferModel("Cheese Burger","The Burgers","150","100 SAR", R.mipmap.ic_launcher_foreground));
        list.add(new OfferModel("Cheese Burger","The Burgers","150","100 SAR", R.mipmap.ic_launcher_foreground));

        GridViewAdapter adapter = new GridViewAdapter(this, list);
        gridView.setAdapter(adapter);


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