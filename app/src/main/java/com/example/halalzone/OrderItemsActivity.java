package com.example.halalzone;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class OrderItemsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private OrderItemAdapter adapter;
    private DatabaseHelper dbHelper;
    private int orderId;
    private List<OrderItem> orderItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_items);

        recyclerView = findViewById(R.id.orderItemsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Get the orderId passed from the previous activity
        orderId = getIntent().getIntExtra("orderId", -1);

        dbHelper = new DatabaseHelper(this);
        orderItemList = dbHelper.fetchOrderItems(orderId);  // Fetch order items from the database

        adapter = new OrderItemAdapter(orderItemList);
        recyclerView.setAdapter(adapter);  // Set the adapter for RecyclerView
    }
}
