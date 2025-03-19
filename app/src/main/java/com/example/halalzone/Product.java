package com.example.halalzone;

public class Product {
    private int id;
    private String category;
    private String name;
    private String email;
    private double price;
    private double discountPrice;
    private String description;
    private byte[] imageBytes;

    // Constructor
    public Product(int id, String category, String name, String email, double price, double discountPrice, String description, byte[] imageBytes) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.email = email;
        this.price = price;
        this.discountPrice = discountPrice;
        this.description = description;
        this.imageBytes = imageBytes;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }
}
