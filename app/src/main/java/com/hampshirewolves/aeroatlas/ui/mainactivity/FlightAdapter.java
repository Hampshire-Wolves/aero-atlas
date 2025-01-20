package com.hampshirewolves.aeroatlas.ui.mainactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hampshirewolves.aeroatlas.R;
import com.hampshirewolves.aeroatlas.databinding.FlightsRecyclerBinding;
import com.hampshirewolves.aeroatlas.model.Flights;

import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.FlightViewHolder> {

    private List<Flights> flightsList;

    private Context context;

    private final RecyclerViewInterface recyclerViewInterface;

    public FlightAdapter(RecyclerViewInterface recyclerViewInterface, Context context, List<Flights> flightsList) {
        this.recyclerViewInterface = recyclerViewInterface;
        this.context = context;
        this.flightsList = flightsList;
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

        return new FlightViewHolder(binding, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {
        Flights flights = flightsList.get(position);

        holder.flightsRecyclerBinding.setFlightItem(flights);

    }

    @Override
    public int getItemCount() {
        return flightsList.size();
    }

    public static class FlightViewHolder extends RecyclerView.ViewHolder {

        FlightsRecyclerBinding flightsRecyclerBinding;

        public FlightViewHolder(FlightsRecyclerBinding flightsRecyclerBinding, RecyclerViewInterface recyclerViewInterface) {
            super(flightsRecyclerBinding.getRoot());
            this.flightsRecyclerBinding = flightsRecyclerBinding;
        }
    }
}
