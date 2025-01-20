package com.hampshirewolves.aeroatlas.service;

import com.hampshirewolves.aeroatlas.model.City;
import com.hampshirewolves.aeroatlas.model.Flight;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface AeroAtlasApiService {

    @GET("cities")
    Call<List<City>> getAllCities();

    @GET("cities/{id}")
    Call<City> getCityById(@Path("id") long id);

    @GET("flights/offers")
    Call<List<Flight>> getFlightOffers(@QueryMap Map<String, String> queryParams);
}
