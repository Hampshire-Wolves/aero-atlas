package com.hampshirewolves.aeroatlas.ui.mainactivity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationBarView;
import com.hampshirewolves.aeroatlas.R;
import com.hampshirewolves.aeroatlas.ui.fragments.discoverpage.DiscoverPageFragment;
import com.hampshirewolves.aeroatlas.ui.fragments.homepage.HomepageFragment;
import com.hampshirewolves.aeroatlas.ui.fragments.auth.UserAuthFragment;
import com.hampshirewolves.aeroatlas.ui.fragments.randomiserpage.RandomiserFragment;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    NavigationBarView bottomNavBar;
    HomepageFragment homepageFragment = new HomepageFragment();
    DiscoverPageFragment discoverPageFragment = new DiscoverPageFragment();
    UserAuthFragment userAuthFragment = new UserAuthFragment();
    RandomiserFragment randomiserFragment= new RandomiserFragment();

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
                    .replace(R.id.baseFragment, userAuthFragment)
                    .commit();
            return true;
        }

        if (item.getItemId() == R.id.randomiserButton) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.baseFragment, randomiserFragment)
                    .commit();
            return true;
        }

        return false;
    }
}