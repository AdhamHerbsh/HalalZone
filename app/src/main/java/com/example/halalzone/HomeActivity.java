//package com.example.halalzone;
//
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.widget.GridView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.widget.Toolbar;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.ActionBarDrawerToggle;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.GravityCompat;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//import androidx.drawerlayout.widget.DrawerLayout;
//
//import com.google.android.material.navigation.NavigationView;
//
//public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
//
//
//    private DrawerLayout drawerLayout;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_home);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawlayout), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        Toolbar toolbar = findViewById(R.id.toolbar);
//
//        setSupportActionBar(toolbar);
//
//        drawerLayout = findViewById(R.id.drawlayout);
//        NavigationView navigationView = findViewById(R.id.nav_view);
//
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
//
//        drawerLayout.addDrawerListener(toggle);
//
//        toggle.syncState();
//
//        if (savedInstanceState() == null){
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ItemFragment()).commit();
//            navigationView.setCheckedItem(R.id.nav_home);
//            }
//        }
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item){
//            switch(item.getItemId()){
//
//                case R.id.nav_home:
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ItemFragment()).commit();
//                    break;
//
//                return true;
//            }
//        }
//
//        @Override
//        public void onBackPressed() {
//            if (drawerLayout.isDrawerOpen(GravityCompat.START)){
//                drawerLayout.closeDrawer(GravityCompat.START);
//
//            }else{
//                super.onBackPressed();
//
//            }
//        }
//
//}
//


package com.example.halalzone;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawlayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
}

}