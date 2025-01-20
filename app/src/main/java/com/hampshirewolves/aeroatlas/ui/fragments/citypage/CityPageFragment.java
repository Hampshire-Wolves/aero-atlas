package com.hampshirewolves.aeroatlas.ui.fragments.citypage;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hampshirewolves.aeroatlas.R;
import com.hampshirewolves.aeroatlas.databinding.FragmentCityPageBinding;

public class CityPageFragment extends Fragment {
    private FragmentCityPageBinding fragmentCityPageBinding;
    private CityPageViewModel cityPageViewModel;

    public CityPageFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        cityPageViewModel = new ViewModelProvider(this).get(CityPageViewModel.class);
        fragmentCityPageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_city_page, container, false);

        View view = fragmentCityPageBinding.getRoot();

        fragmentCityPageBinding.setClickHandler(new CityPageClickHandlers(requireActivity()));

        cityPageViewModel.getCity().observe(getViewLifecycleOwner(), city -> {
            fragmentCityPageBinding.setCity(city);
        });

        Bundle args = getArguments();
        if (args != null) {
            long id = args.getLong("id");
            cityPageViewModel.fetchCityById(id);
        }

        return view;
    }
}
