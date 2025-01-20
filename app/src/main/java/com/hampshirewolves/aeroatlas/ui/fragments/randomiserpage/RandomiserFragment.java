package com.hampshirewolves.aeroatlas.ui.fragments.randomiserpage;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


import com.hampshirewolves.aeroatlas.R;
import com.hampshirewolves.aeroatlas.model.City;
import com.hampshirewolves.aeroatlas.ui.fragments.citypage.CityPageFragment;
import com.hampshirewolves.aeroatlas.ui.mainactivity.MainActivity;

public class RandomiserFragment extends Fragment {

    Dialog dialog;
    Button randomiserPageRandomButton;
    Button randomiserPageCloseButton;
    ImageView randomiserPageBackButton;


    public RandomiserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_randomiser, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RandomiserViewModel viewModel = new ViewModelProvider(this).get(RandomiserViewModel.class);
        viewModel.getRandomCityLiveData().observe(getViewLifecycleOwner(), city -> {
            if (city != null) {
                openCityPageFragment(city);
        } else {
            Log.e("CityPageFragment", "City data is null");
        }
        });

        randomiserPageRandomButton = view.findViewById(R.id.randomiserPageRandomButton);
        randomiserPageRandomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.fetchRandomCity();
            }
        });

        randomiserPageBackButton= view.findViewById(R.id.randomiserPageBackButton);
        randomiserPageBackButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        });
    }
    private void openCityPageFragment(City city) {
        CityPageFragment cityPageFragment = new CityPageFragment();
        Bundle args = new Bundle();
        args.putLong("id", city.getId());
        cityPageFragment.setArguments(args);

        Log.d("RandomiserFragment", "Replacing with CityPageFragment");
        getParentFragmentManager().
                beginTransaction()
                .replace(R.id.baseFragment, cityPageFragment)
                .addToBackStack(null)
                .commit();
    }

}