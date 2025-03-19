package com.example.halalzone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddVideoActivity extends AppCompatActivity {
    private EditText editTextVideoId;
    private DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_video);
        dbHelper = new DatabaseHelper(this);
        editTextVideoId = findViewById(R.id.editTextVideoId);
        Button buttonAddVideo = findViewById(R.id.buttonAddVideo);
        buttonAddVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String videoId = editTextVideoId.getText().toString().trim();

                if (!videoId.isEmpty()) {
                    dbHelper.addVideo(videoId);
                    Toast.makeText(AddVideoActivity.this, "Video Added Successfully!", Toast.LENGTH_SHORT).show();
                    editTextVideoId.setText(""); // Clear the input
                } else {
                    Toast.makeText(AddVideoActivity.this, "Please enter a Video ID", Toast.LENGTH_SHORT).show();
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