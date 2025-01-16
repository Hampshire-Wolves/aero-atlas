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

public class CityAttractionsRepository {

    private List<CityAttractions> cityAttractions = new ArrayList<>();
    private MutableLiveData<List<CityAttractions>> mutableLiveData = new MutableLiveData<>();

    private Application application;

    public CityAttractionsRepository(Application application) {this.application = application;}

    public MutableLiveData<List<CityAttractions>> getMutableLiveData() {

        AeroAtlasApiService aeroAtlasApiService = RetrofitInstance.getService();

        Call<List<CityAttractions>> call = aeroAtlasApiService.getAllCityAttractions();

        call.enqueue(new Callback<List<CityAttractions>>() {
            @Override
            public void onResponse(Call<List<CityAttractions>> call, Response<List<CityAttractions>> response) {

                List<CityAttractions> cityAttractions = response.body();
                mutableLiveData.setValue(cityAttractions);
            }

            @Override
            public void onFailure(Call<List<CityAttractions>> call, Throwable t) {
                Log.i("Get Request for CityAttractions", t.getMessage());

            }
        });
        return mutableLiveData;
    }


}
