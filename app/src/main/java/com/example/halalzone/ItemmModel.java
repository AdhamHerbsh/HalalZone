package com.example.halalzone;

import android.graphics.Bitmap;

public class ItemmModel {
    private int itemId;
    private String name;
    private String business;
    private String oldPrice;
    private String newPrice;
    private String description;
    private Bitmap image;

    public ItemmModel(int itemId, String name, String business, String description,  String oldPrice, String newPrice, Bitmap image) {
        this.itemId = itemId;
        this.name = name;
        this.business = business;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.description = description;
        this.image = image;
    }

    public int getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public String getBusiness() {
        return business;
    }

    public String getOldprice() {
        return oldPrice;
    }

    public String getNewprice() {
        return newPrice;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getdescription() {
        return description;
    }
}
