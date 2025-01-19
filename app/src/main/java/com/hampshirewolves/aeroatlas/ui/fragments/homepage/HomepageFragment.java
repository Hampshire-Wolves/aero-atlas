package com.hampshirewolves.aeroatlas.ui.fragments.homepage;

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
import android.widget.SearchView;
import android.widget.Toast;

import com.hampshirewolves.aeroatlas.R;
import com.hampshirewolves.aeroatlas.ui.fragments.citypage.CityPageFragment;
import com.hampshirewolves.aeroatlas.databinding.FragmentHomepageBinding;
import com.hampshirewolves.aeroatlas.model.City;
import com.hampshirewolves.aeroatlas.ui.adapters.CityAdapter;
import com.hampshirewolves.aeroatlas.ui.mainactivity.MainActivityViewModel;
import com.hampshirewolves.aeroatlas.ui.mainactivity.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public class HomepageFragment extends Fragment implements RecyclerViewInterface {
    private RecyclerView recyclerView;
    private List<City> cityList;
    private CityAdapter cityAdapter;
    private MainActivityViewModel mainActivityViewModel;
    private FragmentHomepageBinding fragmentHomepageBinding;
    private SearchView citysearchbar;
    private ArrayList<City> filteredList = new ArrayList<>();
    private static final String TAG = "HomepageFragment";

    public HomepageFragment() {}

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
        recyclerView = fragmentHomepageBinding.homepageCityRecyclerView;

        cityAdapter = new CityAdapter(cityList, this, this.getContext());
        recyclerView.setAdapter(cityAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        cityAdapter.notifyDataSetChanged();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        fragmentHomepageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_homepage, container, false);

        View view = fragmentHomepageBinding.getRoot();

        getAllCities();

        citysearchbar = view.findViewById(R.id.homepage_city_search_bar);
        citysearchbar.clearFocus();

        citysearchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

       return view;
    }

    private void filterList(String newText) {
        filteredList.clear();
        String lowerCaseText = newText.toLowerCase();

        Predicate<City> matchesQuery = city -> city.getName().toLowerCase().contains(lowerCaseText)
                || city.getCountry().toLowerCase().contains(lowerCaseText);

        cityList.stream()
                .filter(matchesQuery)
                .forEach(filteredList::add);

        if (filteredList.isEmpty()) {
            Toast.makeText(getActivity(), "No cities found!", Toast.LENGTH_SHORT).show();
        }

        cityAdapter.setFilteredList(filteredList);
    }

    @Override
    public void onItemClick(int position) {
        City selectedCity;

        if (filteredList == null || filteredList.isEmpty()) {
            selectedCity = cityList.get(position);
        } else {
            selectedCity = filteredList.get(position);
        }

        CityPageFragment cityPageFragment = new CityPageFragment();

        Bundle bundle = new Bundle();
        bundle.putLong("id", selectedCity.getId());
        cityPageFragment.setArguments(bundle);

        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.baseFragment, cityPageFragment)
                .addToBackStack(null)
                .commit();
    }
}