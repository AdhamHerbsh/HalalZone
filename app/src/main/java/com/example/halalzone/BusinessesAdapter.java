package com.example.halalzone;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class BusinessesAdapter extends CursorAdapter {
    public BusinessesAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.business_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Assuming your cursor has columns: "billingnumber",, "serviceprovider" ,"billtype" ,"transacationdate"


        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewEmail = view.findViewById(R.id.textViewEmail);
        TextView textViewPhone = view.findViewById(R.id.textViewDesc);

        // Set data to views from cursor
        textViewName.setText(cursor.getString(cursor.getColumnIndexOrThrow("name")));
        textViewEmail.setText(cursor.getString(cursor.getColumnIndexOrThrow("email")));
        textViewPhone.setText(cursor.getString(cursor.getColumnIndexOrThrow("phone")));


    }

}
