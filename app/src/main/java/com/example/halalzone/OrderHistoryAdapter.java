package com.example.halalzone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderViewHolder> {
    private List<OrderHistory> orderHistoryList;
    private OnOrderClickListener onOrderClickListener;

    public interface OnOrderClickListener {
        void onOrderClick(int orderId);  // Callback for order click
    }

    public OrderHistoryAdapter(List<OrderHistory> orderHistoryList, OnOrderClickListener onOrderClickListener) {
        this.orderHistoryList = orderHistoryList;
        this.onOrderClickListener = onOrderClickListener;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order_history, parent, false);
        return new OrderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        OrderHistory orderHistory = orderHistoryList.get(position);
        holder.orderId.setText(String.format("Order ID: %d", orderHistory.getOrderId()));
        holder.orderDate.setText(orderHistory.getOrderDate());
        holder.totalAmount.setText(String.format("$ %.2f", orderHistory.getTotalAmount()) + " ("+orderHistory.getpaymentMethod()+")");

        holder.itemView.setOnClickListener(v -> {
            // Trigger the onOrderClickListener when an order is clicked
            onOrderClickListener.onOrderClick(orderHistory.getOrderId());
        });
    }

    @Override
    public int getItemCount() {
        return orderHistoryList.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderId, orderDate, totalAmount;

        public OrderViewHolder(View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.orderId);
            orderDate = itemView.findViewById(R.id.orderDate);
            totalAmount = itemView.findViewById(R.id.totalAmount);
        }
    }
}
