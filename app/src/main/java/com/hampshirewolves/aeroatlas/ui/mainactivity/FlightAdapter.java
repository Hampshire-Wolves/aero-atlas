package com.hampshirewolves.aeroatlas.ui.mainactivity;

import androidx.recyclerview.widget.RecyclerView;

import com.hampshirewolves.aeroatlas.homepage.RecyclerViewInterface;
import com.hampshirewolves.aeroatlas.model.Flights;

import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<> {

    private List<Flights> flightsList;
    private RecyclerViewInterface recyclerViewInterface;


    public static class FlightViewHolder extends RecyclerView.ViewHolder {


    }
}
