package com.hampshirewolves.aeroatlas.ui.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hampshirewolves.aeroatlas.R;
import com.hampshirewolves.aeroatlas.databinding.FlightsRecyclerBinding;
import com.hampshirewolves.aeroatlas.model.Flight;
import com.hampshirewolves.aeroatlas.ui.mainactivity.RecyclerViewInterface;

import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.FlightViewHolder> {
    private List<Flight> flightList;
    private Context context;
    private final RecyclerViewInterface recyclerViewInterface;

    public FlightAdapter(RecyclerViewInterface recyclerViewInterface, Context context, List<Flight> flightList) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        this.flightList = flightList;
    }

    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FlightsRecyclerBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.flights_recycler,
                parent,
                false
        );

        return new FlightViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {
        Flight flight = flightList.get(position);
        holder.flightsRecyclerBinding.setFlight(flight);
        holder.itemView.setOnClickListener(v -> recyclerViewInterface.onItemClick(position));
    }

    @Override
    public int getItemCount() {
        return flightList.size();
    }

    public void swapItems(List<Flight> flightList) {
        this.flightList = flightList;
        notifyDataSetChanged();
    }

    public static class FlightViewHolder extends RecyclerView.ViewHolder {
        FlightsRecyclerBinding flightsRecyclerBinding;

        public FlightViewHolder(@NonNull FlightsRecyclerBinding flightsRecyclerBinding) {
            super(flightsRecyclerBinding.getRoot());
            this.flightsRecyclerBinding = flightsRecyclerBinding;
        }
    }
}
