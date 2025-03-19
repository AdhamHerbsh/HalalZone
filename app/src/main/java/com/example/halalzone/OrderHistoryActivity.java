package com.example.halalzone;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity implements OrderHistoryAdapter.OnOrderClickListener {

    private RecyclerView recyclerView;
    private OrderHistoryAdapter adapter;
    private List<OrderHistory> orderHistoryList;
    private DatabaseHelper dbHelper;
    SharedPreferences sharedPref;
    public String getUserName() {
        return sharedPref.getString("User", "User");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.orderRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));  // Set layout manager

        // Initialize data list and adapter
        orderHistoryList = new ArrayList<>();
        adapter = new OrderHistoryAdapter(orderHistoryList, this);  // Pass listener to adapter
        recyclerView.setAdapter(adapter);  // Set the adapter for RecyclerView

        // Initialize DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Fetch the order history for the given user email
        fetchOrderHistory(getUserName());

        // Set up Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Enable Back Navigation
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void fetchOrderHistory(String email) {
        // Fetch the order history from the database using the helper method
        orderHistoryList.clear();  // Clear the existing list to prevent duplicate data
        List<OrderHistory> fetchedOrders = dbHelper.fetchOrderHistory(email);

        if (fetchedOrders.isEmpty()) {
            // If no orders are found, display a Toast message
            Toast.makeText(this, "No order history found for this email.", Toast.LENGTH_SHORT).show();
        } else {
            // Add the fetched data to the list and notify the adapter
            orderHistoryList.addAll(fetchedOrders);
            adapter.notifyDataSetChanged();
        }
    }

    // Implement the OnOrderClickListener method to handle clicks on an order
    @Override
    public void onOrderClick(int orderId) {
        // Create an intent to open OrderItemsActivity
        Intent intent = new Intent(OrderHistoryActivity.this, OrderItemsActivity.class);
        intent.putExtra("orderId", orderId);  // Pass the orderId to the new activity
        startActivity(intent);  // Start the OrderItemsActivity
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
