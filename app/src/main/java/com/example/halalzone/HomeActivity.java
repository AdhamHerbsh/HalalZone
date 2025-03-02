package com.example.halalzone;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;

public class HomeActivity extends AppCompatActivity {

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tabLayout = findViewById(R.id._TAB_LAYOUT);

        if (savedInstanceState == null) {
            loadFragment(new UserFragment());
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
                fragment = new scanFragment();
                break;
            case 2:
                fragment = new CartFragment();
                break;
            case 3:
//                fragment = new MenuFragment();
                break;
            default:
                Toast.makeText(this, "Invalid Tab Selected", Toast.LENGTH_SHORT).show();
                return;
        }

        if (fragment != null) {
            loadFragment(fragment);
        }
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)  // Enables back navigation
                .commit();
    }
}
