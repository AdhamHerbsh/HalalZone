package com.example.halalzone;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GateFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GateFragment() {
        // Required empty public constructor
    }
    Button _LOGIN_BTN, _CNA_BTN;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GateFragment newInstance(String param1, String param2) {
        GateFragment fragment = new GateFragment();
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
        _CNA_BTN = findViewById(R.id._CNA_BTN);

        _LOGIN_BTN = findViewById(R.id._LOGIN_BTN);

        _CNA_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRegister(v);
            }
        });
        _LOGIN_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLogin(v);
            }
        });
    }

    public void toLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    public void toRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void loadFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id._GATE_FRAGMENT, fragment, tag);
        //transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN); // Consider if you need this
        //transaction.addToBackStack(null); // Consider if you need this
        transaction.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gate, container, false);
    }
}