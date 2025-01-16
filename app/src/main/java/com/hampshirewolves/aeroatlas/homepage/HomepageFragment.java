package com.hampshirewolves.aeroatlas.homepage;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
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
import com.hampshirewolves.aeroatlas.citypage.CityPageFragment;
import com.hampshirewolves.aeroatlas.databinding.FragmentHomepageBinding;
import com.hampshirewolves.aeroatlas.model.City;
import com.hampshirewolves.aeroatlas.ui.mainactivity.CityAdapter;
import com.hampshirewolves.aeroatlas.ui.mainactivity.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;


public class HomepageFragment extends Fragment implements RecyclerViewInterface {

    private RecyclerView recyclerView;
    private List<City> cityList;
    private CityAdapter cityAdapter;
    private MainActivityViewModel mainActivityViewModel;

    private FragmentHomepageBinding fragmentHomepageBinding;
    private static final String CITY_KEY = "city";
    private SearchView citysearchbar;

    private ArrayList<City> filteredList;


    public HomepageFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivityViewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);


    }

    private void getAllCities(){
        mainActivityViewModel.getAllCities().observe(this, new Observer<List<City>>() {
            @Override
            public void onChanged(List<City> cities) {
                cityList = (List<City>) cities;

                displayInRecyclerView();
            }
        });

    }

    private void displayInRecyclerView(){
        recyclerView = fragmentHomepageBinding.cityrecyclerview;
        cityAdapter = new CityAdapter(cityList, this, this.getContext());
        recyclerView.setAdapter(cityAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        cityAdapter.notifyDataSetChanged();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       fragmentHomepageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_homepage, container, false);
        return fragmentHomepageBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        fragmentHomepageBinding.citysearchbar.clearFocus();
        fragmentHomepageBinding.citysearchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filteredList(newText);
                return true;
            }
        });

    }

    private void filteredList(String newText){
        filteredList = new ArrayList<>();

        for (City city : cityList){
            if (city.getName().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(city);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(this.getContext(), "City Not found", Toast.LENGTH_SHORT).show();

        } else {

            cityAdapter.setFilteredList(filteredList);
        }
    }

    @Override
    public void onItemClick(int position) {
       Intent intent = new Intent(this.getContext(), CityPageFragment.class);

    }
}