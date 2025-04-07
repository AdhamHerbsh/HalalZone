package com.example.halalzone;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.halalzone.DatabaseHelper;
import com.example.halalzone.GridViewAdapter;
import com.example.halalzone.OfferModel;
import com.example.halalzone.R;

import java.util.ArrayList;

public class OfferActivity extends AppCompatActivity {

    private GridView gridView;
    private DatabaseHelper databaseHelper;
    private GridViewAdapter adapter;
    public String getUserName() {
        return sharedPref.getString("User", "User");
    }
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);
        sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);

        // Set up Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Initialize GridView and DatabaseHelper
        gridView = findViewById(R.id.grid_view);
        databaseHelper = new DatabaseHelper(this);

        // Fetch items from database asynchronously
        new LoadOffersTask().execute(); // AsyncTask or any background thread for DB fetch
    }

    private class LoadOffersTask extends AsyncTask<Void, Void, ArrayList<OfferModel>> {
        @Override
        protected ArrayList<OfferModel> doInBackground(Void... params) {
            return databaseHelper.getOfferItems(getUserName());
        }

        @Override
        protected void onPostExecute(ArrayList<OfferModel> list) {
            // If no data, handle gracefully
            if (list == null || list.isEmpty()) {
                // Show a message that no offers are available (maybe using a TextView or Toast)
                Toast.makeText(OfferActivity.this, "No offers available", Toast.LENGTH_SHORT).show();
            }

            // Set the adapter once data is loaded
            adapter = new GridViewAdapter(OfferActivity.this, list);
            gridView.setAdapter(adapter);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
