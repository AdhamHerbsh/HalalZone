package com.example.halalzone;

public class itemModel {
    private int id;
    private String name;
    private String price;
    private String disprice;
    private byte[] image;
    private String businessEmail;

    // Constructor for itemModel
    public itemModel(int id, String name, String price, String disprice,  byte[] image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.disprice = disprice;
        this.image = image;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getprice() {
        return price;
    }

    public void setprice(String price) {
        this.price = price;
    }

    public String getDisprice() {
        return disprice;
    }

    public void setDisprice(String disprice) {
        this.disprice = disprice;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }
}
