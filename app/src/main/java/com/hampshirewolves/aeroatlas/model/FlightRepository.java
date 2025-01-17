package com.hampshirewolves.aeroatlas.model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.hampshirewolves.aeroatlas.service.AeroAtlasApiService;
import com.hampshirewolves.aeroatlas.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlightRepository {

private List<Flights> flightsList = new ArrayList<>();
private MutableLiveData<List<Flights>> mutableLiveData = new MutableLiveData<>();
private Application application;

    public FlightRepository(Application application) {this.application = application;
    }

        public MutableLiveData<List<Flights>> getMutableLiveData() {

            AeroAtlasApiService aeroAtlasApiService = RetrofitInstance.getService();

            Call<List<Flights>> call = aeroAtlasApiService.getAllFlights();

            call.enqueue(new Callback<List<Flights>>() {
                @Override
                public void onResponse(Call<List<Flights>> call, Response<List<Flights>> response) {
                    List<Flights> flights = response.body();
                    mutableLiveData.setValue(flights);
                }

                @Override
                public void onFailure(Call<List<Flights>> call, Throwable t) {
                    Log.i("Get Request", t.getMessage());

                }
            });
            return mutableLiveData;
        }
    }

