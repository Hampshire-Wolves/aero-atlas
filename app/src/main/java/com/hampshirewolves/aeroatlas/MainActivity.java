package com.hampshirewolves.aeroatlas;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    NavigationBarView bottomNavBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavBar = findViewById(R.id.bottomnavbar);

        bottomNavBar.setOnItemSelectedListener(this);

        bottomNavBar.setSelectedItemId(R.id.homebutton);

    }

//    Declare each fragment here i.e homepage, city view, etc

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.homebutton)
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.baseFragment, FRAGMENT_NAME)
                    .commit();
        return false;
    }
}