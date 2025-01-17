package com.hampshirewolves.aeroatlas.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hampshirewolves.aeroatlas.R;
import com.hampshirewolves.aeroatlas.databinding.CityItemLayoutBinding;
import com.hampshirewolves.aeroatlas.model.City;
import com.hampshirewolves.aeroatlas.ui.mainactivity.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {
    private List<City> cityList;
    private final RecyclerViewInterface recyclerViewInterface;
    private Context context;

    public CityAdapter(List<City> cityList, RecyclerViewInterface recyclerViewInterface, Context context) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.cityList = cityList;
        this.context = context;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CityItemLayoutBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.city_item_layout,
                parent,
                false

        );
        return new CityViewHolder(binding, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        City city = cityList.get(position);

        holder.cityItemLayoutBinding.setCityList(city);
        holder.itemView.setOnClickListener(v -> {
            if (recyclerViewInterface != null) {
                recyclerViewInterface.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public void setFilteredList(ArrayList<City> filteredList) {
        this.cityList = filteredList;
        notifyDataSetChanged();
    }


    public static class CityViewHolder extends RecyclerView.ViewHolder {
        CityItemLayoutBinding cityItemLayoutBinding;

        public CityViewHolder(CityItemLayoutBinding cityItemLayoutBinding, RecyclerViewInterface recyclerViewInterface) {
            super(cityItemLayoutBinding.getRoot());
            this.cityItemLayoutBinding = cityItemLayoutBinding;
        }
    }
}