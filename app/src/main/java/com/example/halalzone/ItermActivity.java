package com.example.halalzone;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ItermActivity extends AppCompatActivity {
    SharedPreferences sharedPref;
    public String getUserName() {
        return sharedPref.getString("User", "User");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iterm);
        sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);

        // Set up Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable Back Navigation
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Get the itemId from the intent
        int itemId = getIntent().getIntExtra("ITEM_ID", -1);

        // If valid itemId, fetch item data
        if (itemId != -1) {
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            ItemmModel item = dbHelper.getItemById(itemId);

            // Update the UI with item details
            if (item != null) {
                // Set the item name
                TextView itemName = findViewById(R.id.textView4);
                itemName.setText(item.getName());

                // Set the item business
                TextView itemBusiness = findViewById(R.id.textView5);
                itemBusiness.setText(item.getBusiness());

                // Set the item description
                TextView itemDescription = findViewById(R.id.textView6);
                itemDescription.setText(item.getdescription());

                // Set the item price (new and old)
                TextView itemPrice = findViewById(R.id.textView7);
                itemPrice.setText(item.getNewprice() + " USD");

                // Set the item image
                ImageView itemImage = findViewById(R.id.itemImageView);
                itemImage.setImageBitmap(item.getImage());

                // Handle Add to Cart button click
                Button addToCartButton = findViewById(R.id.containedButton);
                addToCartButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Add the item to the cart
                        boolean isAdded = dbHelper.addToCart(item.getItemId(),getUserName());
                        if (isAdded) {
                            Toast.makeText(ItermActivity.this, "Item added to cart!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ItermActivity.this, "Failed to add item to cart.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
