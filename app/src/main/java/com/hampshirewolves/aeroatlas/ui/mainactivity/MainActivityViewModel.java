package com.hampshirewolves.aeroatlas.ui.mainactivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.hampshirewolves.aeroatlas.model.City;
import com.hampshirewolves.aeroatlas.model.CityRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

private CityRepository cityRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.cityRepository = new CityRepository(application);
    }

    public LiveData<List<City>> getAllCities() {
        return cityRepository.getMutableLiveData();
    }
}
