package com.example.halalzone;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFragment.
     */
    // TODO: Rename and change types and number of parameters
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

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




        return view;
    }
}