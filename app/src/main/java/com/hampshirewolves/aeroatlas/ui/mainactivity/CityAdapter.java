package com.hampshirewolves.aeroatlas.ui.mainactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hampshirewolves.aeroatlas.R;
import com.hampshirewolves.aeroatlas.databinding.CityrecyclerBinding;
import com.hampshirewolves.aeroatlas.homepage.RecyclerViewInterface;
import com.hampshirewolves.aeroatlas.model.City;

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

        CityrecyclerBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.cityrecycler,
                parent,
                false

        );
        return new CityViewHolder(binding, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        City city = cityList.get(position);
        holder.cityrecyclerBinding.setCityList(city);
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

        CityrecyclerBinding cityrecyclerBinding;


        public CityViewHolder(CityrecyclerBinding cityrecyclerBinding, RecyclerViewInterface recyclerViewInterface) {
            super(cityrecyclerBinding.getRoot());
            this.cityrecyclerBinding = cityrecyclerBinding;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerViewInterface != null) {
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
