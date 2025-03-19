package com.example.halalzone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.halalzone.databinding.ActivityAddProductBinding;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddProductActivity extends AppCompatActivity {
    ActivityAddProductBinding binding;
    DatabaseHelper databaseHelper;
    SharedPreferences sharedPref;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;
    private byte[] imageBytes;

    public String getUserName() {
        return sharedPref.getString("Business", "No Name");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this);
        sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);

        binding.btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });


        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String category_id = binding.editTextCategory.getText().toString().trim();
                String Name = binding.editTextName.getText().toString().trim();
                String desc = binding.editTextDescription.getText().toString().trim();
                String business_email = getUserName();
                double price = 0, dis_price = 0;
                try {
                    price = Double.parseDouble(binding.editTextPrice.getText().toString().trim());
                    dis_price = Double.parseDouble(binding.editTextDisPrice.getText().toString().trim());
                } catch (NumberFormatException e) {
                    // Handle the error (e.g., show a toast message)
                    Toast.makeText(AddProductActivity.this, "Invalid price input", Toast.LENGTH_SHORT).show();
                }
                String[] nonHalalIngredients = {
                        "Pork",
                        "Bacon",
                        "Ham",
                        "Sausage",
                        "Gelatin",
                        "Lard",
                        "Alcohol",
                        "Ethanol",
                        "Enzymes",
                        "Carrageenan",
                        "Carmine",
                        "Rennet",
                        "Flavorings",
                        "Improperly slaughtered"
                };
                boolean containsNonHalal = false;
                for (String ingredient : nonHalalIngredients) {
                    if (desc.toUpperCase().contains(ingredient.toUpperCase())) {
                        containsNonHalal = true;
                        break; // No need to check further if one non-halal ingredient is found
                    }
                }
                if (containsNonHalal) {
                    Toast.makeText(getApplicationContext(), "Non-halal ingredients detected", Toast.LENGTH_LONG).show();
                } else {

                    Boolean insertuser = databaseHelper.addproduct(category_id, Name, business_email, price, dis_price, desc, imageBytes);
                    if(insertuser){
                        Toast.makeText(AddProductActivity.this, "success" , Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddProductActivity.this, BusinessHomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(AddProductActivity.this, "failed" , Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            binding.productImageView.setImageURI(imageUri);

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                imageBytes = stream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}