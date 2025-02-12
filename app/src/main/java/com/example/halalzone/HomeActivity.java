package com.example.halalzone;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;


    public class HomeActivity extends AppCompatActivity {

        private FrameLayout userFrame;
        private TabLayout tabLayout;

        private static final String TAG_USER_FRAGMENT = "userFragment";
        private static final String TAG_SCAN_FRAGMENT = "scanFragment";
        private static final String TAG_SHOP_FRAGMENT = "shopFragment";
        private static final String TAG_MENU_FRAGMENT = "menuFragment";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // EdgeToEdge.enable(this); // Assuming this is a custom utility, keep it if needed
            setContentView(R.layout.activity_home);

            userFrame = findViewById(R.id._USER_FRAME); // Changed to camelCase
            tabLayout = findViewById(R.id._TAB_LAYOUT); // Changed to camelCase

            // Initialize with UserFragment
            if (savedInstanceState == null) {
                loadFragment(new UserFragment(), TAG_USER_FRAGMENT);
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
                    // Optionally, handle reselection (e.g., scroll to top)
                    handleTabReselection(tab.getPosition());
                }
            });
        }

        private void handleTabSelection(int position) {
            Fragment fragment = null;
            String tag = null;

            switch (position) {
                case 0:
                    fragment = new UserFragment();
                    tag = TAG_USER_FRAGMENT;
                    break;
                case 1:
                    //fragment = new ScanFragment(); // Assuming you have a ScanFragment
                    //tag = TAG_SCAN_FRAGMENT;
                    Toast.makeText(HomeActivity.this, "Scan", Toast.LENGTH_SHORT).show();
                    return; // Exit early since no fragment is loaded
                case 2:
                    //fragment = new ShopFragment(); // Assuming you have a ShopFragment
                    //tag = TAG_SHOP_FRAGMENT;
                    Toast.makeText(HomeActivity.this, "Shop", Toast.LENGTH_SHORT).show();
                    return; // Exit early since no fragment is loaded
                case 3:
                    //fragment = new MenuFragment(); // Assuming you have a MenuFragment
                    //tag = TAG_MENU_FRAGMENT;
                    Toast.makeText(HomeActivity.this, "Menu", Toast.LENGTH_SHORT).show();
                    return; // Exit early since no fragment is loaded
            }

            if (fragment != null) {
                loadFragment(fragment, tag);
            }
        }

        private void handleTabReselection(int position) {
            // Example: Scroll to top if the UserFragment is reselected
            if (position == 0) {
                Fragment currentFragment = getSupportFragmentManager().findFragmentByTag(TAG_USER_FRAGMENT);
                if (currentFragment instanceof UserFragment) {
                    //((UserFragment) currentFragment).scrollToTop(); // Assuming UserFragment has a scrollToTop method
                    Toast.makeText(HomeActivity.this, "UserFragment reselected", Toast.LENGTH_SHORT).show();
                }
            }
        }

        private void loadFragment(Fragment fragment, String tag) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id._USER_FRAME, fragment, tag);
            //transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN); // Consider if you need this
            //transaction.addToBackStack(null); // Consider if you need this
            transaction.commit();
        }
    }