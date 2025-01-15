package com.hampshirewolves.aeroatlas.citypage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hampshirewolves.aeroatlas.R;
import com.hampshirewolves.aeroatlas.model.CityAttractions;
import com.hampshirewolves.aeroatlas.ui.mainactivity.CityAttractionsAdapter;

import java.util.List;

public class CityPageFragment extends Fragment {


    private List<CityAttractions> cityAttractionsList;
    private RecyclerView cityAttractionsRecyclerView;
    private CityAttractionsAdapter cityAttractionsAdapter;

    public CityPageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_city_page, container, false);
    }



}