package com.hampshirewolves.aeroatlas;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<String> mockPlaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the mock places list
        initializeMockPlaces();

        // Get references to UI elements
        MaterialButton greenButton = findViewById(R.id.login);
        MaterialButton blueButton = findViewById(R.id.register);
        TextView randomCityTextView = findViewById(R.id.random_city_name);

        // Add click listener for the green button
        greenButton.setOnClickListener(v -> {
            // Hide both buttons
            greenButton.setVisibility(View.GONE);
            blueButton.setVisibility(View.GONE);

            // Show a random city name in the TextView
            String randomCity = getRandomCity();
            randomCityTextView.setText(randomCity);

            // Make the TextView visible
            randomCityTextView.setVisibility(View.VISIBLE);
        });

        // Add click listener for the blue button
        blueButton.setOnClickListener(v -> {

            // Hide both buttons
            greenButton.setVisibility(View.GONE);
            blueButton.setVisibility(View.GONE);

            // Show a random city name in the TextView
            String randomCity = getRandomCity();
            randomCityTextView.setText(randomCity);

            // Make the TextView visible
            randomCityTextView.setVisibility(View.VISIBLE);
        });
    }

    // Initialize the list of mock places
    private void initializeMockPlaces() {
        mockPlaces = new ArrayList<>();
        mockPlaces.add("Eiffel Tower, Paris, France");
        mockPlaces.add("Colosseum, Rome, Italy");
        mockPlaces.add("Great Wall of China, China");
        mockPlaces.add("Machu Picchu, Peru");
        mockPlaces.add("Statue of Liberty, New York, USA");
        mockPlaces.add("Sydney Opera House, Sydney, Australia");
    }

    // Get a random city name from the list
    private String getRandomCity() {
        Random random = new Random();
        return mockPlaces.get(random.nextInt(mockPlaces.size()));
    }
}
