package com.company.geonotifier.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.company.geonotifier.model.PlaceGroupWithPlaces;
import com.company.geonotifier.repository.PlaceGroupRepository;

import java.util.List;

public class PlaceGroupsFragmentViewModel extends AndroidViewModel {

    private PlaceGroupRepository repository;
    private LiveData<List<PlaceGroupWithPlaces>> allPlaceGroupsWithPlaces;

    public PlaceGroupsFragmentViewModel(@NonNull Application application) {
        super(application);
        repository = new PlaceGroupRepository(application);
        allPlaceGroupsWithPlaces = repository.getAll();
    }

    public void insert(PlaceGroupWithPlaces placeGroupWithPlaces) {
        repository.insert(placeGroupWithPlaces);
    }

    public void delete(PlaceGroupWithPlaces placeGroupWithPlaces) {
        repository.delete(placeGroupWithPlaces);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public LiveData<List<PlaceGroupWithPlaces>> getAllPlaceGroupsWithPlaces() {
        return allPlaceGroupsWithPlaces;
    }
}