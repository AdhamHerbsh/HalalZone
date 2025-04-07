package com.example.halalzone;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class scanFragment extends Fragment {

    private Button btn_scan;
    private TextView txtResult;
    private ActivityResultLauncher<ScanOptions> barLauncher;
    private DatabaseHelper databaseHelper;  // Database helper instance

    public scanFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scan, container, false);

        btn_scan = view.findViewById(R.id.btn_scan);
        txtResult = view.findViewById(R.id.txt_result); // Add this TextView in XML
        databaseHelper = new DatabaseHelper(requireContext()); // Initialize database helper

        btn_scan.setOnClickListener(v -> scanCode());

        barLauncher = registerForActivityResult(new ScanContract(), result -> {
            if (result.getContents() != null) {
                checkBarcodeInDatabase(result.getContents());
            }
        });

        return view;
    }

    private void scanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }

    private void checkBarcodeInDatabase(String barcode) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM HALAL_ITEMS WHERE barcode = ?", new String[]{barcode});

        if (cursor.moveToFirst()) {
            showResultDialog("✅ Halal", "This product is Halal.");
            txtResult.setText("✅ Halal");
            txtResult.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        } else {
            showResultDialog("❌ Not Halal", "This product is NOT Halal.");
            txtResult.setText("❌ Not Halal");
            txtResult.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }
        cursor.close();
    }

    private void showResultDialog(String title, String message) {
        new AlertDialog.Builder(requireContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss())
                .show();
    }
}
