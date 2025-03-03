package com.example.halalzone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.halalzone.databinding.ActivityAddBusinessBinding;


public class AddBusinessActivity extends AppCompatActivity {

    ActivityAddBusinessBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBusinessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this);

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.editTextEmail.getText().toString().trim();
                String name = binding.editTextName.getText().toString().trim();
                String password = binding.editTextPassword.getText().toString().trim();
                String Phone = binding.editTextPhone.getText().toString().trim();
                Boolean insertuser = databaseHelper.addbusiness(username,name,password,Phone);
                if(insertuser){
                    Toast.makeText(AddBusinessActivity.this, "success" , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddBusinessActivity.this, AdminHomeActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(AddBusinessActivity.this, "failed" , Toast.LENGTH_SHORT).show();
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