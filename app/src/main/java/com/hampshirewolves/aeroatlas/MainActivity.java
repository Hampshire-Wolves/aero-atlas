package com.hampshirewolves.aeroatlas;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationBarView;
import com.hampshirewolves.aeroatlas.homepage.HomepageFragment;
import com.hampshirewolves.aeroatlas.profilepage.DiscoverPageFragment;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    NavigationBarView bottomNavBar;
    //   TODO  Declare each fragment here i.e homepage, city view, etc
    HomepageFragment homepageFragment = new HomepageFragment();
    DiscoverPageFragment discoverPageFragment = new DiscoverPageFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavBar = findViewById(R.id.bottomnavbar);

        bottomNavBar.setOnItemSelectedListener(this);

        bottomNavBar.setSelectedItemId(R.id.homebutton);

        if (getIntent().hasExtra("navigateTo") && "DiscoverPageFragment".equals(getIntent().getStringExtra("navigateTo"))) {
            getSupportFragmentManager().beginTransaction().replace(R.id.baseFragment, new DiscoverPageFragment()).commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.homebutton) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.baseFragment, homepageFragment)
                    .commit();
            return true;
        }

        if (item.getItemId() == R.id.profileButton) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.baseFragment, discoverPageFragment)
                    .commit();
            return true;
        }

        return false;
    }
}