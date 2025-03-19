package com.example.halalzone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.halalzone.databinding.ActivityAllProductsBinding;
import java.util.List;

public class AllProductsActivity extends AppCompatActivity {
    ActivityAllProductsBinding binding;
    DatabaseHelper databaseHelper;
    ProductAdapter productAdapter;
    List<Product> productList;
    SharedPreferences sharedPref;


    public String getUserName() {
        return sharedPref.getString("Business", "No Name");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);

        databaseHelper = new DatabaseHelper(this);

        // Get products filtered by user email
        productList = databaseHelper.getProductsByEmail(getUserName());

        if (productList.isEmpty()) {
            Toast.makeText(this, "No products found", Toast.LENGTH_SHORT).show();
        }

        // Set up RecyclerView
        productAdapter = new ProductAdapter(this, productList, databaseHelper);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(productAdapter);

        // Button to add a new product
        binding.btnAddProduct.setOnClickListener(v -> {
            Intent intent = new Intent(AllProductsActivity.this, AddProductActivity.class);
            startActivity(intent);
        });
    }
}
