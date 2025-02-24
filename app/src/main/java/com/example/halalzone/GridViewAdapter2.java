package com.example.halalzone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridViewAdapter2 extends ArrayAdapter<itemModel> {

    public GridViewAdapter2(Context context, ArrayList<itemModel> list) {
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.card_item2, parent, false);
        }

        itemModel model = getItem(position);

        TextView textView = itemView.findViewById(R.id.text_view);
        TextView textView3 = itemView.findViewById(R.id.text_view3);
        ImageView imageView = itemView.findViewById(R.id.image_view);

        if (model != null) {
            textView.setText(model.getName());
            textView3.setText(model.getNewprice());
            imageView.setImageResource(model.getImage());
        }

        return itemView;
    }
}
