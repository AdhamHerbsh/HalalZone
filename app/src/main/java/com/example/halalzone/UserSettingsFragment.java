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
import android.util.Log;

public class UserSettingsFragment extends Fragment {

    private DatabaseHelper databaseHelper;

    public UserSettingsFragment() {
        // Required empty public constructor
    }

    public static UserSettingsFragment newInstance(String param1, String param2) {
        UserSettingsFragment fragment = new UserSettingsFragment();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_settings, container, false);

        databaseHelper = new DatabaseHelper(requireContext());

        // Retrieve shared preferences
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("User", ""); // Default is empty string

        TextView userTextView = view.findViewById(R.id.username);
        TextView usermailTextView = view.findViewById(R.id.useremail);

        if (userTextView != null && usermailTextView != null) {
            if (!userEmail.isEmpty()) {
                String userName = databaseHelper.getname(userEmail);
                userTextView.setText(userName);
                usermailTextView.setText(userEmail);
            } else {
                userTextView.setText("Guest");
                usermailTextView.setText("guest@example.com");
            }
        } else {
            Log.e("BlankFragment", "TextView is null! Check your layout file.");
        }

        // Initialize buttons
        LinearLayout sendReport = view.findViewById(R.id.SendReport);
        LinearLayout orderHistory = view.findViewById(R.id.OrderHistory);
        LinearLayout editProfile = view.findViewById(R.id.EditProfile);
        LinearLayout about = view.findViewById(R.id.About);
        LinearLayout logout = view.findViewById(R.id.Logout);

        if (orderHistory != null) {
            orderHistory.setOnClickListener(v -> startActivity(new Intent(getActivity(), OrderHistoryActivity.class)));
        }
        if (sendReport != null) {
            sendReport.setOnClickListener(v -> startActivity(new Intent(getActivity(), SendReportActivity.class)));
        }
        if (editProfile != null) {
            editProfile.setOnClickListener(v -> startActivity(new Intent(getActivity(), EditProfileActivity.class)));
        }
        if (about != null) {
            about.setOnClickListener(v -> startActivity(new Intent(getActivity(), AboutAppActivity.class)));
        }
        if (logout != null) {
            logout.setOnClickListener(this::onClick);
        }

        return view;
    }

    private void onClick(View v) {
        // Clear the user session from SharedPreferences
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("User"); // Remove the stored user email
        editor.apply(); // Apply changes

        // Redirect to GataActivity
        Intent intent = new Intent(getActivity(), GateActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear the back stack
        startActivity(intent);    }
}
