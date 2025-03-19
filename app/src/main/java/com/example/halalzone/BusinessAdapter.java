package com.example.halalzone;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.BusinessViewHolder> {

    private Context context;
    private List<Business> businessList;

    public BusinessAdapter(Context context, List<Business> businessList) {
        this.context = context;
        this.businessList = businessList;
    }

    @NonNull
    @Override
    public BusinessViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_business_card, parent, false);
        return new BusinessViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessViewHolder holder, int position) {
        Business business = businessList.get(position);
        holder.businessName.setText(business.getName());
        holder.businessType.setText(business.getType());

        // Convert byte array to Bitmap
        if (business.getImage() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(business.getImage(), 0, business.getImage().length);
            holder.businessImage.setImageBitmap(bitmap);
        } else {
            holder.businessImage.setImageResource(R.mipmap.ic_launcher_foreground);
        }

        // Click listener for opening business details
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ItemsActivity.class);
            intent.putExtra("business_email", business.getEmail());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return businessList.size();
    }

    static class BusinessViewHolder extends RecyclerView.ViewHolder {
        ImageView businessImage;
        TextView businessName, businessType;

        public BusinessViewHolder(@NonNull View itemView) {
            super(itemView);
            businessImage = itemView.findViewById(R.id.business_image);
            businessName = itemView.findViewById(R.id.business_name);
            businessType = itemView.findViewById(R.id.business_type);
        }
    }
}
