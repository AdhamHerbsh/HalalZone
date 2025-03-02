package com.example.halalzone;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProblemsAdapter extends CursorAdapter {
    public ProblemsAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.problem_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Assuming your cursor has columns: "billingnumber",, "serviceprovider" ,"billtype" ,"transacationdate"


        TextView textViewOrderID = view.findViewById(R.id.textViewOrderID);
        TextView textViewOrderItemID = view.findViewById(R.id.textViewOrderItemID);
        TextView textViewDate = view.findViewById(R.id.textViewDate);
        TextView textViewDesc = view.findViewById(R.id.textViewDesc);

        // Set data to views from cursor
        textViewOrderID.setText(cursor.getString(cursor.getColumnIndexOrThrow("order_id")));
        textViewOrderItemID.setText(cursor.getString(cursor.getColumnIndexOrThrow("order_item_id")));
        textViewDate.setText(cursor.getString(cursor.getColumnIndexOrThrow("date")));
        textViewDesc.setText(cursor.getString(cursor.getColumnIndexOrThrow("problem_description")));


    }

}
