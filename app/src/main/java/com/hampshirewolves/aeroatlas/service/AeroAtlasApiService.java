package com.hampshirewolves.aeroatlas.service;

import com.hampshirewolves.aeroatlas.model.City;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AeroAtlasApiService {

    @GET("cities")
    Call<List<City>> getAllCities();

    @GET("cities/{id}")
    Call<City> getCityById(@Path("id") long id);
}
