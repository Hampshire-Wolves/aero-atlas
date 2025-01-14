package com.hampshirewolves.aeroatlas;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<String> mockPlaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeMockPlaces();

        Button randomiseButton = findViewById(R.id.randomise_button);
        randomiseButton.setOnClickListener(v -> {
            String randomCity = getRandomCity();
            openMockCityPage(randomCity);
        });
    }

    private void initializeMockPlaces() {
        mockPlaces = new ArrayList<>();
        mockPlaces.add("Eiffel Tower, Paris, France");
        mockPlaces.add("Colosseum, Rome, Italy");
        mockPlaces.add("Great Wall of China, China");
        mockPlaces.add("Machu Picchu, Peru");
        mockPlaces.add("Statue of Liberty, New York, USA");
        mockPlaces.add("Sydney Opera House, Sydney, Australia");
    }

    private String getRandomCity() {
        Random random = new Random();
        return mockPlaces.get(random.nextInt(mockPlaces.size()));
    }

    private void openMockCityPage(String cityName) {
        // Set the content view to the Mock City Page layout
        setContentView(R.layout.activity_mock_city_page);

        // Update the random city name on Mock City Page
        TextView cityTextView = findViewById(R.id.city_name);
        cityTextView.setText(cityName);
    }
}
