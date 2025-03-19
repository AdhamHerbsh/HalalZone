package com.example.halalzone;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BusinessHomeActivity extends AppCompatActivity {
    LinearLayout add, mng, warning;
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView businessImage;
    SharedPreferences sharedPref;
    public String getUserName() {
        return sharedPref.getString("Business", "No Name");
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_business_home);
        sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        businessImage = findViewById(R.id.businessImage);  // Ensure the correct ID is used


        // Fetch the image from the database
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        byte[] imageBytes = dbHelper.getImage(getUserName());  // Replace '1' with the actual business ID

        if (imageBytes != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            businessImage.setImageBitmap(bitmap);
        } else {
            // Handle case where image is null (e.g., set a default image or show a placeholder)
            businessImage.setImageResource(R.drawable.circle_user_solid);  // Use a default image
        }



        add = findViewById(R.id.addd);
        add.setOnClickListener(this::toadditem);

        mng = findViewById(R.id.mng);
        mng.setOnClickListener(this::tomng);

        warning = findViewById(R.id.warning);
        warning.setOnClickListener(this::towarn);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    public void toadditem(View view) {
        Intent intent = new Intent(this, AddProductActivity.class);
        startActivity(intent);
    }

    public void tomng(View view) {
        Intent intent = new Intent(this, AllProductsActivity.class);
        startActivity(intent);
    }

    public void towarn(View view) {
        Intent intent = new Intent(this, BusinessWarningsActivity.class);
        startActivity(intent);
    }
    public void onImageClick(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);

                // Save the image to the database
                DatabaseHelper databaseHelper = new DatabaseHelper(this);
                // Assuming business ID is 1 for this example, replace with actual business ID
                boolean success = databaseHelper.updateBusinessImage(getUserName(), bitmap);

                if (success) {
                    Toast.makeText(this, "Image saved successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed to save image.", Toast.LENGTH_SHORT).show();
                }

                // Set the image to the ImageView
                businessImage.setImageBitmap(bitmap);
            } catch (Exception e) {
                Toast.makeText(this, "Error loading image", Toast.LENGTH_SHORT).show();
            }
        }
    }

}