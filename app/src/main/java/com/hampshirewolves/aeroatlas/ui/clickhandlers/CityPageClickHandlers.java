package com.hampshirewolves.aeroatlas.ui.clickhandlers;

import android.view.View;

import androidx.fragment.app.FragmentActivity;

public class CityPageClickHandlers {

    private final FragmentActivity activity;

    public CityPageClickHandlers(FragmentActivity activity) {
        this.activity = activity;
    }

    public void onBackBtnClicked(View view) {
        // Navigate back to the previous fragment
        activity.getSupportFragmentManager().popBackStack();
    }
}
