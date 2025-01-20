package com.hampshirewolves.aeroatlas.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.hampshirewolves.aeroatlas.model.City;
import com.hampshirewolves.aeroatlas.service.AeroAtlasApiService;
import com.hampshirewolves.aeroatlas.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityRepository {
    private List<City> cities = new ArrayList<>();
    private MutableLiveData<List<City>> citiesLiveData = new MutableLiveData<>();
    private MutableLiveData<City> singleCityLiveData = new MutableLiveData<>();

    private MutableLiveData<City> randomCityLiveData = new MutableLiveData<>();
    private AeroAtlasApiService service;
    private Application application;

    public CityRepository(Application application) {
        this.application = application;
        service = RetrofitInstance.getService();
    }

    public MutableLiveData<List<City>> getCitiesLiveData() {
        Call<List<City>> call = service.getAllCities();

        call.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {

                List<City> cities = response.body();
                citiesLiveData.setValue(cities);
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                Log.i("GET /cities", t.getMessage());

            }
        });

        return citiesLiveData;
    }

    public MutableLiveData<City> getCityByIdLiveData(long id) {
        Call<City> call = service.getCityById(id);

        call.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                if (response.isSuccessful() && response.body() != null) {
                    singleCityLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                Log.i("GET /cities/{id}", t.getMessage());
            }
        });

        return singleCityLiveData;
    }

    public MutableLiveData<City> getRandomCityLiveData() {
        Call<City> call = service.getRandomCity();

        call.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                if (response.isSuccessful() && response.body() != null) {
                    randomCityLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {
                Log.i("GET /cities/random", t.getMessage());
            }
        });

        return randomCityLiveData;
    }
}
