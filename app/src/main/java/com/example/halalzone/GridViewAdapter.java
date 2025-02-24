package com.example.halalzone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GridViewAdapter extends ArrayAdapter<OfferModel> {

    public GridViewAdapter(Context context, ArrayList<OfferModel> list) {
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.card_item, parent, false);
        }

        OfferModel model = getItem(position);

        TextView textView = itemView.findViewById(R.id.text_view);
        TextView textView1 = itemView.findViewById(R.id.text_view1);
        TextView textView2 = itemView.findViewById(R.id.text_view2);
        TextView textView3 = itemView.findViewById(R.id.text_view3);
        ImageView imageView = itemView.findViewById(R.id.image_view);

        if (model != null) {
            textView.setText(model.getName());
            textView1.setText(model.getBusiness());
            textView2.setText(model.getOldprice());
            textView3.setText(model.getNewprice());
            imageView.setImageResource(model.getImage());
        }

        return itemView;
    }
}
