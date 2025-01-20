package com.hampshirewolves.aeroatlas.ui.adapters;

import static androidx.databinding.library.baseAdapters.BR.cityList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hampshirewolves.aeroatlas.R;
import com.hampshirewolves.aeroatlas.databinding.PlacesToVisitRecyclerBinding;
import com.hampshirewolves.aeroatlas.model.City;
import com.hampshirewolves.aeroatlas.model.CityAttractions;
import com.hampshirewolves.aeroatlas.ui.mainactivity.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;

public class CityAttractionsAdapter extends RecyclerView.Adapter<CityAttractionsAdapter.CityAttractionsViewHolder> {

    private Context context;
    private List<CityAttractions> cityAttractionsList;
    private final RecyclerViewInterface recyclerViewInterface;

    public CityAttractionsAdapter(Context context, List<CityAttractions> cityAttractionsList, RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.cityAttractionsList = cityAttractionsList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public CityAttractionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PlacesToVisitRecyclerBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.places_to_visit_recycler,
                parent,
                false
        );
        return new CityAttractionsViewHolder(binding, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull CityAttractionsViewHolder holder, int position) {
        CityAttractions cityAttractions = cityAttractionsList.get(position);
        holder.placesToVisitRecyclerBinding.setCityattractionsinfo(cityAttractions);

        holder.itemView.setOnClickListener(v -> {
            if (recyclerViewInterface != null) {
                recyclerViewInterface.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cityAttractionsList.size();
    }

    public void setFilteredList(List<CityAttractions> filteredList) {
        this.cityAttractionsList = filteredList;
        notifyDataSetChanged();
    }

    public static class CityAttractionsViewHolder extends RecyclerView.ViewHolder {
        PlacesToVisitRecyclerBinding placesToVisitRecyclerBinding;

        public CityAttractionsViewHolder(PlacesToVisitRecyclerBinding placesToVisitRecyclerBinding, RecyclerViewInterface recyclerViewInterface) {
            super(placesToVisitRecyclerBinding.getRoot());
            this.placesToVisitRecyclerBinding = placesToVisitRecyclerBinding;

            itemView.setOnClickListener(v -> {
                if (recyclerViewInterface != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        recyclerViewInterface.onItemClick(position);
                    }
                }
            });
        }
    }
}
