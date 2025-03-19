package com.example.halalzone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ProblemsAdapter extends CursorAdapter {
    private DatabaseHelper dbHelper;

    public ProblemsAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
        dbHelper = new DatabaseHelper(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.problem_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewOrderID = view.findViewById(R.id.textViewOrderID);
        TextView textViewOrderItemID = view.findViewById(R.id.textViewOrderItemID);
        TextView textViewDate = view.findViewById(R.id.textViewDate);
        TextView textViewDesc = view.findViewById(R.id.textViewDesc);
        TextView textViewStatus = view.findViewById(R.id.textViewStatus);
        Button buttonUpdateStatus = view.findViewById(R.id.buttonUpdateStatus);

        // ✅ Corrected column name: _id instead of id
        int problemId = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
        String orderID = cursor.getString(cursor.getColumnIndexOrThrow("order_id"));
        String orderItemID = cursor.getString(cursor.getColumnIndexOrThrow("order_item_id"));
        String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
        String description = cursor.getString(cursor.getColumnIndexOrThrow("problem_description"));
        String status = cursor.getString(cursor.getColumnIndexOrThrow("status"));

        textViewOrderID.setText(orderID);
        textViewOrderItemID.setText(orderItemID);
        textViewDate.setText(date);
        textViewDesc.setText(description);
        textViewStatus.setText(status);

        if ("Done".equals(status)) {
            buttonUpdateStatus.setText("Mark as Pending");
            buttonUpdateStatus.setBackgroundColor(context.getResources().getColor(R.color.red));
        } else {
            buttonUpdateStatus.setText("Mark as Done");
            buttonUpdateStatus.setBackgroundColor(context.getResources().getColor(R.color.green));
        }

        buttonUpdateStatus.setOnClickListener(v -> {
            String newStatus = "Done".equals(status) ? "Pending" : "Done";
            updateProblemStatus(problemId, newStatus);
            Toast.makeText(context, "Status updated to " + newStatus, Toast.LENGTH_SHORT).show();
            refreshCursor();
        });
    }

    private void updateProblemStatus(int problemId, String newStatus) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status", newStatus);

        db.update("PROBLEM", values, "_id = ?", new String[]{String.valueOf(problemId)});
        db.close();
    }

    private void refreshCursor() {
        Cursor newCursor = dbHelper.getReadableDatabase().rawQuery("SELECT * FROM PROBLEM", null);
        swapCursor(newCursor);
    }
}
