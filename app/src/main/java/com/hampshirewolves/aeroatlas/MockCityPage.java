package com.hampshirewolves.aeroatlas;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MockCityPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_city_page);

        // Retrieve the city name passed via Intent
        String cityName = getIntent().getStringExtra("CITY_NAME");

        // Find the TextView and set the city name
        TextView cityNameTextView = findViewById(R.id.city_name);
        cityNameTextView.setText(cityName);
    }
}
