package com.example.halalzone;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddWarningActivity extends AppCompatActivity {
    private EditText editTextEmail, editTextNote;
    private Button btnSubmit;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_warning);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextNote = findViewById(R.id.editTextNote);
        btnSubmit = findViewById(R.id.btnSubmit);
        databaseHelper = new DatabaseHelper(this);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String note = editTextNote.getText().toString().trim();
                String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

                if (!email.isEmpty() && !note.isEmpty()) {
                    boolean isInserted = databaseHelper.addWarning(email, note, currentDate);
                    if (isInserted) {
                        Toast.makeText(AddWarningActivity.this, "Warning Added", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(AddWarningActivity.this, "Failed to Add", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddWarningActivity.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
