package com.example.halalzone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;




public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<CartItem> cartItemList;
    private String username;
    private CartFragment cartFragment;  // Use CartFragment instead of Context
    private DatabaseHelper dbHelper;

    // Modify the constructor to accept CartFragment
    public CartAdapter(List<CartItem> cartItemList, String username, CartFragment cartFragment) {
        this.cartItemList = cartItemList;
        this.username = username;
        this.cartFragment = cartFragment;  // Store the CartFragment instance
        this.dbHelper = new DatabaseHelper(cartFragment.getContext());  // Initialize DatabaseHelper with fragment's context
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(cartFragment.getContext()).inflate(R.layout.cart_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CartItem cartItem = cartItemList.get(position);
        holder.itemName.setText(cartItem.getName());
        holder.itemPrice.setText(String.valueOf(cartItem.getPrice()));
        holder.itemQty.setText(String.valueOf(cartItem.getQty()));  // Set initial quantity

        // Decrement button logic
        holder.decrementButton.setOnClickListener(v -> {
            if (cartItem.getQty() > 1) {
                cartItem.setQty(cartItem.getQty() - 1);
                dbHelper.updateCartItemQuantity(cartItem.getItemId(), cartItem.getQty(), username);
                notifyItemChanged(position);  // Refresh the item
                cartFragment.updateTotalPrice();  // Update total price
            }
        });

        // Increment button logic
        holder.incrementButton.setOnClickListener(v -> {
            cartItem.setQty(cartItem.getQty() + 1);
            dbHelper.updateCartItemQuantity(cartItem.getItemId(), cartItem.getQty(), username);
            notifyItemChanged(position);  // Refresh the item
            cartFragment.updateTotalPrice();  // Update total price
        });

        // Delete button logic
        holder.deleteButton.setOnClickListener(v -> {
            deleteItem(position); // Delete the item at this position
        });
    }


    private void deleteItem(int position) {
        // Remove the item from the list
        CartItem itemToDelete = cartItemList.get(position);
        cartItemList.remove(position);
        notifyItemRemoved(position);

        // Remove the item from the database
        dbHelper.removeCartItem(itemToDelete.getItemId(), username);

        // Update the total price in CartFragment
        if (cartFragment != null) {
            cartFragment.updateTotalPrice();  // Call the method in CartFragment to update the total price
        }
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemPrice, itemQty;
        ImageView deleteButton, decrementButton, incrementButton;

        public ViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            itemPrice = itemView.findViewById(R.id.item_price);
            itemQty = itemView.findViewById(R.id.item_qty);  // Bind quantity TextView
            deleteButton = itemView.findViewById(R.id.item_remove);
            decrementButton = itemView.findViewById(R.id.btn_decrement);  // Bind decrement button
            incrementButton = itemView.findViewById(R.id.btn_increment);  // Bind increment button
        }
    }
}
