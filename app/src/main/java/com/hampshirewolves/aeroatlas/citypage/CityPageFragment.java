package com.hampshirewolves.aeroatlas.citypage;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hampshirewolves.aeroatlas.R;
import com.hampshirewolves.aeroatlas.databinding.FragmentCityPageBinding;

public class CityPageFragment extends Fragment {
    private FragmentCityPageBinding fragmentCityPageBinding;

    public CityPageFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentCityPageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_city_page, container, false);

        Bundle args = getArguments();
        if (args != null) {
            long id = args.getLong("id");
        }

        return fragmentCityPageBinding.getRoot();
    }
}