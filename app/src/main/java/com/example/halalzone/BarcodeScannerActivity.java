package com.example.halalzone;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class BarcodeScannerActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText edtBarcode;
    private Button btnScan, btnSave;
    private String scannedBarcode = "";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode_scanner);

        databaseHelper = new DatabaseHelper(this);

        edtBarcode = findViewById(R.id.edtBarcode);
        btnScan = findViewById(R.id.btnScan);
        btnSave = findViewById(R.id.btnSave);

        btnScan.setOnClickListener(v -> scanBarcode());

        btnSave.setOnClickListener(v -> saveBarcodeToDatabase());
    }

    private void scanBarcode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Scan a barcode");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);

        barcodeLauncher.launch(options);
    }

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher =
            registerForActivityResult(new ScanContract(), result -> {
                if (result.getContents() != null) {
                    scannedBarcode = result.getContents();
                    edtBarcode.setText(scannedBarcode);
                }
            });

    private void saveBarcodeToDatabase() {
        String barcode = edtBarcode.getText().toString().trim();

        if (barcode.isEmpty()) {
            Toast.makeText(this, "Please enter both barcode and item name", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.execSQL("INSERT INTO HALAL_ITEMS (barcode) VALUES (?)", new Object[]{barcode});
        db.close();

        new AlertDialog.Builder(this)
                .setTitle("Success")
                .setMessage("Barcode saved successfully!")
                .setPositiveButton("OK", (dialog, which) -> {
                    edtBarcode.setText("");
                })
                .show();
    }
}
