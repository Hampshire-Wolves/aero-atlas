package com.hampshirewolves.aeroatlas.service;

import com.hampshirewolves.aeroatlas.model.City;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AeroAtlasApiService {

    @GET //Needs Endpoint
    Call<List<City>> getAllCities();
}
