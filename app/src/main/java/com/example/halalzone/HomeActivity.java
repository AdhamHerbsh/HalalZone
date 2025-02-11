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


    FrameLayout userFrame;

    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

    userFrame = findViewById(R.id._USER_FRAME);
    tabLayout = findViewById(R.id._TAB_LAYOUT);

        getSupportFragmentManager().beginTransaction().replace(R.id._USER_FRAME, new UserFragment()).addToBackStack(null).commit();

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                               @Override
                                               public void onTabSelected(TabLayout.Tab tab) {

                                                   Fragment fragment = null;

                                                   switch (tab.getPosition()){

                                                       case 0:
                                                           fragment = new UserFragment();
                                                           break;
                                                       case 1:

                                                           Toast.makeText(HomeActivity.this, "Scan", Toast.LENGTH_SHORT).show();

                                                           break;

                                                       case 2:
                                                           Toast.makeText(HomeActivity.this, "Shop", Toast.LENGTH_SHORT).show();

                                                           break;

                                                       case 3:
                                                           Toast.makeText(HomeActivity.this, "Menu", Toast.LENGTH_SHORT).show();

                                                           break;

                                                   }

                                                   getSupportFragmentManager().beginTransaction().replace(R.id._USER_FRAME, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();


                                               }

                                               @Override
                                               public void onTabUnselected(TabLayout.Tab tab) {

                                               }

                                               @Override
                                               public void onTabReselected(TabLayout.Tab tab) {

                                               }
                                           }
                );


    }
}