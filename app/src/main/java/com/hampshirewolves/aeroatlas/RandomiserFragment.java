package com.hampshirewolves.aeroatlas;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RandomiserFragment extends Fragment {

    private List<String> mockPlaces;

    public RandomiserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_randomiser, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeMockPlaces();

        Button randomiseButton = view.findViewById(R.id.randomise_button);
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
        // Create an Intent to start CityPage activity
        Intent intent = new Intent(requireContext(), CityPage.class);
        intent.putExtra("CITY_NAME", cityName);
        startActivity(intent);
    }
}