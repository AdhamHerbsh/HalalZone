package com.example.halalzone;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private SharedPreferences sharedPref;

    // Get the username from SharedPreferences
    public String getUserName() {
        return sharedPref.getString("User", "User");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sharedPref = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);

        tabLayout = findViewById(R.id._TAB_LAYOUT);

        if (savedInstanceState == null) {
            loadFragment(new UserFragment()); // Load default fragment only if there's no saved state
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                handleTabSelection(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // No action needed
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Toast.makeText(HomeActivity.this, "Tab Reselected: " + tab.getPosition(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleTabSelection(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new UserFragment();
                break;
            case 1:
                fragment = new scanFragment(); // Changed from scanFragment to ScanFragment
                break;
            case 2:
                fragment = CartFragment.newInstance(getUserName());
                break;
            case 3:
                fragment = new UserSettingsFragment();
                break;
            default:
                Toast.makeText(this, "Invalid Tab Selected", Toast.LENGTH_SHORT).show();
                return;
        }

        loadFragment(fragment);
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null) // Enables back navigation
                .commit();
    }
}
