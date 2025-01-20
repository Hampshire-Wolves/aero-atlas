package com.hampshirewolves.aeroatlas.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.hampshirewolves.aeroatlas.model.Flight;
import com.hampshirewolves.aeroatlas.service.AeroAtlasApiService;
import com.hampshirewolves.aeroatlas.service.RetrofitInstance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlightRepository {
    private MutableLiveData<List<Flight>> flightsData = new MutableLiveData<>();
    private Application application;
    public FlightRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Flight>> getFlights(String originCode, String destinationCode, String departureDate, String returnDate, MutableLiveData<List<Flight>> liveData) {

        AeroAtlasApiService aeroAtlasApiService = RetrofitInstance.getService();

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("originLocationCode", originCode);
        queryParams.put("destinationLocationCode", destinationCode);
        queryParams.put("departureDate", departureDate);

        if (returnDate != null && !returnDate.isEmpty()) {
            queryParams.put("returnDate", returnDate);
        }
        queryParams.put("adults", "1");
        queryParams.put("max", "5");

        Call<List<Flight>> call = aeroAtlasApiService.getFlightOffers(queryParams);

        call.enqueue(new Callback<List<Flight>>() {
            @Override
            public void onResponse(Call<List<Flight>> call, Response<List<Flight>> response) {
                List<Flight> flights = response.body();
                flightsData.setValue(flights);
            }

            @Override
            public void onFailure(Call<List<Flight>> call, Throwable t) {
                flightsData.setValue(null);
                Log.i("GET /flights/offers", t.getMessage());
            }
        });
        return flightsData;
    }
}

