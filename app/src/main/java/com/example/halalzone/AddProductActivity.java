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

public class AddProductActivity extends AppCompatActivity {
    ActivityAddProductBinding binding;
    DatabaseHelper databaseHelper;
    SharedPreferences sharedPref;

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

                Boolean insertuser = databaseHelper.addproduct(category_id,Name,business_email,price,dis_price,desc);
                if(insertuser){
                    Toast.makeText(AddProductActivity.this, "success" , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddProductActivity.this, BusinessHomeActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(AddProductActivity.this, "failed" , Toast.LENGTH_SHORT).show();
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}