package com.example.halalzone;

import android.graphics.Bitmap;

public class OfferModel {
    private int itemId;
    private String name;
    private String business;
    private String oldPrice;
    private String newPrice;
    private Bitmap image;

    public OfferModel(int itemId, String name, String business, String oldPrice, String newPrice, Bitmap image) {
        this.itemId = itemId;
        this.name = name;
        this.business = business;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
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
}





