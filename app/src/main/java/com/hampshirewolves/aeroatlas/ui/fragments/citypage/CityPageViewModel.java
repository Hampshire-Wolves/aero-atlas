package com.hampshirewolves.aeroatlas.ui.fragments.citypage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hampshirewolves.aeroatlas.model.City;
import com.hampshirewolves.aeroatlas.repository.CityRepository;

public class CityPageViewModel extends AndroidViewModel {
    private final MutableLiveData<City> cityLiveData = new MutableLiveData<>();
    private final CityRepository repository;

    public CityPageViewModel(@NonNull Application application) {
        super(application);
        this.repository = new CityRepository(application);
    }

    public LiveData<City> getCity() {
        return cityLiveData;
    }

    public void fetchCityById(long id) {
        LiveData<City> repositoryLiveData = repository.getCityByIdLiveData(id);
        repositoryLiveData.observeForever(cityLiveData::setValue);
    }
}
