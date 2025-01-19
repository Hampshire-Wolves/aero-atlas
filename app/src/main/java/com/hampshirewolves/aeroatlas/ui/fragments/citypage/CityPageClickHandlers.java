package com.hampshirewolves.aeroatlas.ui.fragments.citypage;

import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.hampshirewolves.aeroatlas.R;
import com.hampshirewolves.aeroatlas.ui.fragments.flightselectorpage.FlightSelectorFragment;

public class CityPageClickHandlers {

    private final FragmentActivity activity;

    public CityPageClickHandlers(FragmentActivity activity) {
        this.activity = activity;
    }

    public void onBackBtnClicked(View view) {
        activity.getSupportFragmentManager().popBackStack();
    }

    public void onViewFlightInformationClicked(View view) {
        FlightSelectorFragment flightSelectorFragment = new FlightSelectorFragment();
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.baseFragment, flightSelectorFragment);
        transaction.addToBackStack(null); // Optional: Add to back stack
        transaction.commit();
    }
}

