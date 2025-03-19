package com.example.halalzone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class UserFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public UserFragment() {
        // Required empty public constructor
    }


    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        databaseHelper = new DatabaseHelper(getContext());

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("User", "User"); // Default is "User" if no email is found
        TextView userTextView = view.findViewById(R.id.textView3);

        if (!userEmail.isEmpty()) {
            // Fetch name from database
            String userName = databaseHelper.getname(userEmail);
            userTextView.setText("Hello " + userName);
        } else {
            userTextView.setText("Guest"); // Default text if email is missing
        }

        LinearLayout qiblaCard = view.findViewById(R.id._QIBLA_CARD);
        qiblaCard.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), QiblaActivity.class);
            startActivity(intent);
        });

        LinearLayout resCard = view.findViewById(R.id._RESTAURANT_CARD);
        resCard.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), RestuarantsActivity.class);
            startActivity(intent);
        });

        LinearLayout shopsCard = view.findViewById(R.id._SHOP_CARD);
        shopsCard.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ShopsActivity.class);
            startActivity(intent);
        });

        LinearLayout prayersCard = view.findViewById(R.id._PRAYER_CARD);
        prayersCard.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), PrayertimeActivity.class);
            startActivity(intent);
        });

        LinearLayout offerCard = view.findViewById(R.id._OFFER_CARD);
        offerCard.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), OfferActivity.class);
            startActivity(intent);
        });

        LinearLayout VCard = view.findViewById(R.id._VIDEOS_CARD);
        VCard.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), YoutubeWebviewActivity.class);
            startActivity(intent);
        });




        return view;
    }
}