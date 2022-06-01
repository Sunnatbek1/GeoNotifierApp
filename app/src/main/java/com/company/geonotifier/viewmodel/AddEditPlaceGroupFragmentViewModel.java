package com.company.geonotifier.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.company.geonotifier.model.Place;
import com.company.geonotifier.model.PlaceGroupWithPlaces;
import com.company.geonotifier.repository.PlaceGroupRepository;
import com.company.geonotifier.repository.PlaceRepository;

import java.util.List;

public class AddEditPlaceGroupFragmentViewModel extends AndroidViewModel {

    private PlaceGroupRepository placeGroupRepository;
    private PlaceRepository placeRepository;

    private MutableLiveData<Place> selected = new MutableLiveData<>();
    private LiveData<List<Place>> allPlaces;

    public AddEditPlaceGroupFragmentViewModel(@NonNull Application application) {
        super(application);
        placeGroupRepository = new PlaceGroupRepository(application);
        placeRepository = new PlaceRepository(application);
        allPlaces = placeRepository.getAll();
    }

    public void insert(PlaceGroupWithPlaces placeGroupWithPlaces) {
        placeGroupRepository.insert(placeGroupWithPlaces);
    }

    public void update(PlaceGroupWithPlaces placeGroupWithPlaces) {
        placeGroupRepository.update(placeGroupWithPlaces);
    }

    public void delete(PlaceGroupWithPlaces placeGroupWithPlaces) {
        placeGroupRepository.delete(placeGroupWithPlaces);
    }

    public LiveData<Place> getSelectedPlace() {
        return selected;
    }

    public void selectPlace(Place place) {
        selected.setValue(place);
    }

    public LiveData<List<Place>> getAllPlaces() {
        return allPlaces;
    }
}
