package com.company.geonotifier.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.company.geonotifier.model.Place;
import com.company.geonotifier.repository.PlaceRepository;

public class NamePlaceDialogViewModel extends BaseViewModel<Place> {

    public NamePlaceDialogViewModel(@NonNull Application application) {
        super(application);
        repository = new PlaceRepository(application);
        allItems = repository.getAll();
    }
}
