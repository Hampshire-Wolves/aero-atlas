package com.hampshirewolves.aeroatlas.ui.fragments.randomiserpage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hampshirewolves.aeroatlas.model.City;
import com.hampshirewolves.aeroatlas.repository.CityRepository;

public class RandomiserViewModel extends AndroidViewModel {
    private final MutableLiveData<City> randomCityLiveData = new MutableLiveData<>();
    private final CityRepository repository;

    public RandomiserViewModel(@NonNull Application application) {
        super(application);
        this.repository = new CityRepository(application);
    }

    public LiveData<City> getRandomCityLiveData() {
        return randomCityLiveData;
    }

    public void fetchRandomCity() {
       repository.getRandomCityLiveData();

    }
}
