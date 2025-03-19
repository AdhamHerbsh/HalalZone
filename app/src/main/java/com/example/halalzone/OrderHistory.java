package com.example.halalzone;
public class OrderHistory {
    private int orderId;  // Add field for orderId
    private String orderDate;
    private String paymentMethod;
    private double totalAmount;

    public OrderHistory(int orderId, String orderDate, double totalAmount, String paymentMethod) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.paymentMethod = paymentMethod;
        this.totalAmount = totalAmount;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }
    public String getpaymentMethod() {
        return paymentMethod;
    }


    public double getTotalAmount() {
        return totalAmount;
    }
}
