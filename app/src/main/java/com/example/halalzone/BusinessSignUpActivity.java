package com.example.halalzone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.halalzone.databinding.ActivityAddBusinessBinding;
import com.example.halalzone.databinding.ActivityBusinessSignUpBinding;

public class BusinessSignUpActivity extends AppCompatActivity {
    ActivityBusinessSignUpBinding binding;
    DatabaseHelper databaseHelper;
    SharedPreferences sharedPref;
    public void saveBusiness(String username) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("Business", username);
        editor.apply();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBusinessSignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this);
        sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.editTextEmail.getText().toString().trim();
                String name = binding.editTextName.getText().toString().trim();
                String password = binding.editTextPassword.getText().toString().trim();
                String Phone = binding.editTextPhone.getText().toString().trim();
                int selectedRadioButtonId = binding.radioGroupBusinessType.getCheckedRadioButtonId();
                String businessType = selectedRadioButtonId == R.id.radioShop ? "Shop" : "Restaurant";

                Boolean insertuser = databaseHelper.addbusiness(username,name,password,Phone,businessType);
                if(insertuser){
                    saveBusiness(username);
                    Toast.makeText(BusinessSignUpActivity.this, "success" , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(BusinessSignUpActivity.this, BusinessHomeActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(BusinessSignUpActivity.this, "failed" , Toast.LENGTH_SHORT).show();
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