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

public class CityRepository {

    private List<City> cities = new ArrayList<>();
    private MutableLiveData<List<City>> mutableLiveData = new MutableLiveData<>();

    private Application application;

    public CityRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<City>> getMutableLiveData() {

        AeroAtlasApiService aeroAtlasApiService = RetrofitInstance.getService();

        Call<List<City>> call = aeroAtlasApiService.getAllCities();

        call.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {

                List<City> cities = response.body();
                mutableLiveData.setValue(cities);
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                Log.i("Get Request", t.getMessage());

            }
        });
        return mutableLiveData;
    }
}
