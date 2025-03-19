package com.example.halalzone;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    private static final String ARG_USERNAME = "username";
    private String username;
    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private TextView carttotalprice;
    private ArrayList<CartItem> cartItems;
    private DatabaseHelper dbHelper;
    private Button checkoutButton;
    private RadioGroup paymentGroup;
    public CartFragment() {
        // Required empty public constructor
    }

    // newInstance() method to create a new instance of CartFragment with username as argument
    public static CartFragment newInstance(String username) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME, username);  // Pass the username argument
        fragment.setArguments(args);  // Set the arguments for the fragment
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the username argument from the fragment's arguments
        if (getArguments() != null) {
            username = getArguments().getString(ARG_USERNAME);
        }
    }

    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        // Initialize RecyclerView and DatabaseHelper
        recyclerView = view.findViewById(R.id.recycler_view);
        dbHelper = new DatabaseHelper(getContext());  // Initialize DatabaseHelper
        cartItems = new ArrayList<>();

        // Initialize the carttotalprice TextView
        carttotalprice = view.findViewById(R.id.cart_total_price);

        // Set up RecyclerView with a linear layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Fetch cart items from the database using the username
        fetchCartItems();

        checkoutButton = view.findViewById(R.id.containedButton);
        checkoutButton.setOnClickListener(v -> {
            if (cartItems.isEmpty()) {
                Toast.makeText(getContext(), "Your cart is empty!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Fetch selected payment method
            paymentGroup = view.findViewById(R.id.payment_method_group);
            int selectedId = paymentGroup.getCheckedRadioButtonId();
            String paymentMethod;
            if (selectedId == R.id.radio_card) {
                paymentMethod = "Card";
            } else {
                paymentMethod = "Cash";
            }

            double totalPrice = dbHelper.getTotalPriceForUserCart(username);
            long orderId = dbHelper.placeOrder(username, totalPrice, cartItems, paymentMethod);

            if (orderId != -1) {
                dbHelper.clearCart(username);  // Clear the cart after placing an order
                cartItems.clear();
                cartAdapter.notifyDataSetChanged();
                carttotalprice.setText("Total: 0 USD");
                Toast.makeText(getContext(), "Order placed successfully!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), "Failed to place order!", Toast.LENGTH_LONG).show();
            }
        });



        return view;
    }

    // Fetch cart items from the database for the specified username
    private void fetchCartItems() {
        cartItems = dbHelper.getCartItems(username);  // Fetch cart items from the database
        if (cartItems != null && !cartItems.isEmpty()) {
            // Set the adapter if cart items are found
            cartAdapter = new CartAdapter(cartItems, username, CartFragment.this);  // Pass the CartFragment instance
            recyclerView.setAdapter(cartAdapter);

            // Set the total price
            double totalPrice = dbHelper.getTotalPriceForUserCart(username);
            carttotalprice.setText("Total: " + totalPrice + " USD");

        } else {
            // Show a message if the cart is empty
            Toast.makeText(getContext(), "Your cart is empty.", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to update the total price after an item is deleted
    public void updateTotalPrice() {
        double totalPrice = dbHelper.getTotalPriceForUserCart(username);  // Get the updated total price
        carttotalprice.setText("Total: " + totalPrice + " USD");  // Update the UI
    }

    @Override
    public void onResume() {
        super.onResume();
        // Refresh the cart items when the fragment is resumed
        fetchCartItems();
    }
}
