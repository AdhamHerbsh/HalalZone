package com.example.halalzone;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<OfferModel> offerList;

    public GridViewAdapter(Context context, ArrayList<OfferModel> offerList) {
        this.context = context;
        this.offerList = offerList;
    }

    @Override
    public int getCount() {
        return offerList.size();
    }

    @Override
    public Object getItem(int position) {
        return offerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        }

        TextView nameTextView = convertView.findViewById(R.id.text_view);
        TextView businessTextView = convertView.findViewById(R.id.text_view1);
        TextView oldPriceTextView = convertView.findViewById(R.id.text_view2);
        TextView newPriceTextView = convertView.findViewById(R.id.text_view3);
        ImageView imageView = convertView.findViewById(R.id.image_view);

        OfferModel offer = offerList.get(position);

        nameTextView.setText(offer.getName());
        businessTextView.setText(offer.getBusiness());
        oldPriceTextView.setText(offer.getOldprice());
        newPriceTextView.setText(offer.getNewprice());

        Bitmap image = offer.getImage();
        if (image != null) {
            imageView.setImageBitmap(image);
        } else {
            // Set a placeholder image if the Bitmap is null
            imageView.setImageResource(R.mipmap.ic_launcher_foreground); // Use your own placeholder image
        }

        // Set OnClickListener to open ItemActivity with item ID
        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ItermActivity.class);
            intent.putExtra("ITEM_ID", offer.getItemId()); // Pass item ID
            context.startActivity(intent);
        });

        return convertView;
    }
}
