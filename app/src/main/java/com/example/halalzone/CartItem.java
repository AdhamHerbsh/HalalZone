package com.example.halalzone;

import android.graphics.Bitmap;

public class CartItem {
    private int itemId;
    private String name;
    private double price;
    private int qty;
    private Bitmap image;  // Added image field

    public CartItem(int itemId, String name, double price, int qty, Bitmap image) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.image = image;
    }

    // Getters and setters
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
