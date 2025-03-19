package com.example.halalzone;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SendReportActivity extends AppCompatActivity {

    private EditText orderIdInput, orderItemIdInput, problemDescriptionInput;
    private Button submitReportButton;
    private DatabaseHelper dbHelper;
    public String getUserName() {
        return sharedPref.getString("User", "User");
    }
    SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_send_report);
        sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        dbHelper = new DatabaseHelper(this);

        // Set up Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Enable Back Navigation
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        orderIdInput = findViewById(R.id.orderIdInput);
        orderItemIdInput = findViewById(R.id.orderItemIdInput);
        problemDescriptionInput = findViewById(R.id.problemDescriptionInput);
        submitReportButton = findViewById(R.id.submitReportButton);
        submitReportButton.setOnClickListener(v -> submitReport());


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void submitReport() {
        // Get the input values
        String orderId = orderIdInput.getText().toString().trim();
        String orderItemId = orderItemIdInput.getText().toString().trim();
        String problemDescription = problemDescriptionInput.getText().toString().trim();
        String date = getCurrentDate();  // You can use the current date or let the user enter it
        String status = "Pending";  // Default status when the report is created
        String note = getUserName();  // You can leave it empty or allow the user to add additional notes

        // Validate input fields
        if (TextUtils.isEmpty(orderId) || TextUtils.isEmpty(orderItemId) || TextUtils.isEmpty(problemDescription)) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        } else {
            // Insert the report into the PROBLEM table
            boolean isInserted = dbHelper.insertProblem(Integer.parseInt(orderId), Integer.parseInt(orderItemId), problemDescription, date, status, note);

            if (isInserted) {
                Toast.makeText(this, "Report submitted successfully", Toast.LENGTH_SHORT).show();
                // Clear the input fields after submission
                orderIdInput.setText("");
                orderItemIdInput.setText("");
                problemDescriptionInput.setText("");
            } else {
                Toast.makeText(this, "Failed to submit the report", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}