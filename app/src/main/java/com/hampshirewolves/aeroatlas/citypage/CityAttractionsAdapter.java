package com.hampshirewolves.aeroatlas.citypage;

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
import com.hampshirewolves.aeroatlas.ui.mainactivity.CityAdapter;

import java.util.ArrayList;

public class CityAttractionsAdapter extends RecyclerView.Adapter<CityAttractionsAdapter.CityAttractionsViewHolder> {

    private Context context;
    private List<CityAttractions> cityAttractionsList;
    private final RecyclerViewInterface recyclerViewInterface;

    public CityAttractionsAdapter(Context context, List<CityAttractions> cityAttractionsList, RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.cityAttractionsList = cityAttractionsList;
        this.recyclerViewInterface = recyclerViewInterface;
    }


    @NonNull
    @Override
    public CityAttractionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CityAttractionsRecyclerBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.card_cell_places_to_visit,
                parent,
                false

        );
        return new CityAttractionsViewHolder(binding, recyclerViewInterface);

    }

    @Override
    public void onBindViewHolder(@NonNull CityAttractionsViewHolder holder, int position) {
        CityAttractions cityAttractions = cityAttractionsList.get(position);
        holder.cityAttractionsBinding.setCityAttractionsList(cityAttractions);
    }

    @Override
    public int getItemCount() {
        return cityAttractionsList.size();
    }

    public void setFilteredList(List<CityAttractions> filteredList) {
        this.cityAttractionsList = filteredList;
        notifyDataSetChanged();
    }


    public static class CityAttractionsViewHolder extends RecyclerView.ViewHolder{

        CityAttractionsBinding cityAttractionsBinding;

        public CityAttractionsViewHolder(CityAttractionsRecyclerBinding cityAttractionsRecyclerBinding, RecyclerViewInterface recyclerViewInterface) {
            super(cityAttractionsRecyclerBinding.getRoot());
            this.cityAttractionsRecyclerBinding = cityAttractionsRecyclerBinding;

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
