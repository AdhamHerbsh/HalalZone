package com.example.halalzone;
public class OrderItem {
    private int itemId;
    private int quantity;
    private double price;
    private double total;

    public OrderItem(int itemId, int quantity, double price, double total) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    public int getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getTotal() {
        return total;
    }
}
