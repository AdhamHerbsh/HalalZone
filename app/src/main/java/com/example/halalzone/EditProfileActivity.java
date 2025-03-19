package com.example.halalzone;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditProfileActivity extends AppCompatActivity {

    private EditText editTextName, editTextPhone;
    private Button btnSave;
    private DatabaseHelper dbHelper;
    private Spinner countryCodeSpinner;
    public String getUserName() {
        return sharedPref.getString("User", "User");
    }
    SharedPreferences sharedPref;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_profile);
        sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        dbHelper = new DatabaseHelper(this);

        editTextName = findViewById(R.id.edit_name);
        editTextPhone = findViewById(R.id.edit_phone);
        btnSave = findViewById(R.id.btn_save);
        countryCodeSpinner = findViewById(R.id.country_code_spinner);
        ArrayAdapter<String> countryCodeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                new String[]{"+33", "+1"});
        countryCodeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countryCodeSpinner.setAdapter(countryCodeAdapter);


        loadUserData();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserData();
            }
        });

        // Set up Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Enable Back Navigation
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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
    private void loadUserData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name, phone FROM user WHERE email = ?", new String[]{String.valueOf(getUserName())});
        if (cursor.moveToFirst()) {
            editTextName.setText(cursor.getString(0));
            editTextPhone.setText(cursor.getString(1));
        }
        cursor.close();
    }

    private void updateUserData() {
        String name = editTextName.getText().toString().trim();
        String phone = countryCodeSpinner.getSelectedItem().toString() + editTextPhone.getText().toString().trim();

        if (name.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (phone.length() > 11) {
            Toast.makeText(this, "Password must be less than 8 numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);

        int rows = db.update("user", values, "email = ?", new String[]{String.valueOf(getUserName())});
        if (rows > 0) {
            Toast.makeText(this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show();
        }
    }
}