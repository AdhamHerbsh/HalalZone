package com.example.halalzone;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context context;
    private List<Product> productList;
    private DatabaseHelper databaseHelper;

    public ProductAdapter(Context context, List<Product> productList, DatabaseHelper databaseHelper) {
        this.context = context;
        this.productList = productList;
        this.databaseHelper = databaseHelper;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productName.setText(product.getName());
        holder.productPrice.setText("Price: $" + product.getPrice());
        holder.productDiscount.setText("Discount: $" + product.getDiscountPrice());
        holder.productDescription.setText(product.getDescription());

        // Load image
        if (product.getImageBytes() != null) {
            Glide.with(context).load(product.getImageBytes()).into(holder.productImage);
        } else {
            holder.productImage.setImageResource(R.mipmap.ic_launcher_foreground);
        }

        // Edit Discount Button
        holder.btnEditDiscount.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Edit Discount Price");

            View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_discount, null);
            EditText editTextDiscount = dialogView.findViewById(R.id.editTextDiscount);
            editTextDiscount.setText(String.valueOf(product.getDiscountPrice()));

            builder.setView(dialogView);
            builder.setPositiveButton("Save", (dialog, which) -> {
                try {
                    double newDiscountPrice = Double.parseDouble(editTextDiscount.getText().toString());
                    boolean updated = databaseHelper.updateDiscountPrice(product.getId(), newDiscountPrice);

                    if (updated) {
                        product.setDiscountPrice(newDiscountPrice);
                        notifyItemChanged(position);
                        Toast.makeText(context, "Discount price updated!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(context, "Invalid input", Toast.LENGTH_SHORT).show();
                }
            });

            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
            builder.show();
        });

        holder.btnDelete.setOnClickListener(v -> {
            // Confirm deletion with the user
            new AlertDialog.Builder(context)
                    .setMessage("Are you sure you want to delete this product?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        // Remove item from the list and update the UI
                        boolean deleted = databaseHelper.deleteProduct(product.getId());
                        if (deleted) {
                            // Remove the item from the list and notify adapter
                            productList.remove(position);
                            notifyItemRemoved(position);
                            Toast.makeText(context, "Product deleted", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Failed to delete product", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice, productDiscount, productDescription;
        ImageView productImage;
        Button btnEditDiscount, btnDelete;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productDiscount = itemView.findViewById(R.id.productDiscount);
            productDescription = itemView.findViewById(R.id.productDescription);
            productImage = itemView.findViewById(R.id.productImage);
            btnEditDiscount = itemView.findViewById(R.id.btnEditDiscount);
            btnDelete = itemView.findViewById(R.id.btnDeleteProduct);
        }
    }
}
