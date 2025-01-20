package com.hampshirewolves.aeroatlas.ui.fragments.flightselectorpage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hampshirewolves.aeroatlas.model.Flight;
import com.hampshirewolves.aeroatlas.repository.FlightRepository;

import java.util.List;

public class FlightSelectorViewModel extends AndroidViewModel {
    private MutableLiveData<List<Flight>> flightsData = new MutableLiveData<>();
    private MutableLiveData<Flight> selectedFlight = new MutableLiveData<>();
    private FlightRepository repository;

    public FlightSelectorViewModel(@NonNull Application application) {
        super(application);
        this.repository = new FlightRepository(application);
    }

    public LiveData<List<Flight>> fetchFlights(String originCode, String destinationCode, String departureDate, String returnDate) {
        repository.getFlights(originCode, destinationCode, departureDate, returnDate, flightsData);
        return flightsData;
    }
//
//    public void selectFlight(Flight flight) {
//        selectedFlight.setValue(flight);
//    }
//
//    public LiveData<Flight> getSelectedFlight() {
//        return selectedFlight;
//    }
}

