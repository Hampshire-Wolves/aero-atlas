package com.hampshirewolves.aeroatlas.homepage;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

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
    private CardView cardView;


    public HomepageFragment() {}

    private void getAllCities() {
        mainActivityViewModel.getAllCities().observe(getViewLifecycleOwner(), new Observer<List<City>>() {
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

        fragmentHomepageBinding.homepageCitySearchBar.clearFocus();
        fragmentHomepageBinding.homepageCitySearchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

/*        CardView cardView = view.findViewById(R.id.cityListing);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new CityPageFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.replace(R.id.baseFragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });*/

       return view;
    }

    private void filteredList(String newText){

    }

    @Override
    public void onItemClick(int position) {
    }
}