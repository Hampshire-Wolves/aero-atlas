package com.hampshirewolves.aeroatlas.ui.fragments.citypage;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hampshirewolves.aeroatlas.R;
import com.hampshirewolves.aeroatlas.databinding.FragmentCityPageBinding;
import com.hampshirewolves.aeroatlas.databinding.PlacesToVisitRecyclerBinding;
import com.hampshirewolves.aeroatlas.model.City;
import com.hampshirewolves.aeroatlas.model.CityAttractions;
import com.hampshirewolves.aeroatlas.ui.adapters.CityAdapter;
import com.hampshirewolves.aeroatlas.ui.adapters.CityAttractionsAdapter;
import com.hampshirewolves.aeroatlas.ui.mainactivity.MainActivityViewModel;
import com.hampshirewolves.aeroatlas.ui.mainactivity.RecyclerViewInterface;

import java.util.List;

public class CityPageFragment extends Fragment implements RecyclerViewInterface {
    private FragmentCityPageBinding fragmentCityPageBinding;
    private CityAttractionsAdapter cityAttractionsAdapter;
    private CityPageViewModel cityPageViewModel;
    private RecyclerView recyclerView;
    private List<CityAttractions> cityAttractionsList;
    private PlacesToVisitRecyclerBinding placesToVisitRecyclerBinding;
    private MainActivityViewModel mainActivityViewModel;
    private List<City> cityList;

    public CityPageFragment() {}
    private void getAllCities() {
        mainActivityViewModel.fetchAllCities().observe(getViewLifecycleOwner(), new Observer<List<City>>() {

            @Override
            public void onChanged(List<City> cities) {
                cityList = cities;
                displayInRecyclerView();
            }
        });
    }

    private void displayInRecyclerView() {
        recyclerView = placesToVisitRecyclerBinding.cityAttractionsItemLayout;

        cityAttractionsAdapter = new CityAttractionsAdapter((Context) cityAttractionsList, (List<CityAttractions>) this, (RecyclerViewInterface) this.getContext());
        recyclerView.setAdapter(cityAttractionsAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        cityAttractionsAdapter.notifyDataSetChanged();
    }

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

    @Override
    public void onItemClick(int position) {

    }
}
