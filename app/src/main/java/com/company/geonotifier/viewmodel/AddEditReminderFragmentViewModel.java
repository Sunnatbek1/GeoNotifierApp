package com.company.geonotifier.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.company.geonotifier.model.Place;
import com.company.geonotifier.model.PlaceGroupWithPlaces;
import com.company.geonotifier.model.ReminderWithNotePlacePlaceGroup;
import com.company.geonotifier.repository.PlaceGroupRepository;
import com.company.geonotifier.repository.PlaceRepository;
import com.company.geonotifier.repository.ReminderRepository;
import com.company.geonotifier.ui.addeditreminder.Selectable;

import java.util.List;

public class AddEditReminderFragmentViewModel extends AndroidViewModel {

    private ReminderRepository reminderRepository;
    private PlaceGroupRepository placeGroupRepository;
    private PlaceRepository placeRepository;

    private MutableLiveData<Selectable> selected = new MutableLiveData<>();
    private LiveData<List<Place>> allPlaces;
    private LiveData<List<PlaceGroupWithPlaces>> allPlaceGroups;

    public AddEditReminderFragmentViewModel(@NonNull Application application) {
        super(application);
        reminderRepository = new ReminderRepository(application);
        placeGroupRepository = new PlaceGroupRepository(application);
        placeRepository = new PlaceRepository(application);
        allPlaces = placeRepository.getAll();
        allPlaceGroups = placeGroupRepository.getAll();
    }

    public void insert(ReminderWithNotePlacePlaceGroup reminder) {
        reminderRepository.insert(reminder);
    }

    public void update(ReminderWithNotePlacePlaceGroup reminder) {
        reminderRepository.update(reminder);
    }

    public void delete(ReminderWithNotePlacePlaceGroup reminder) {
        reminderRepository.delete(reminder);
    }

    public void select(Selectable selectable) {
        selected.setValue(selectable);
    }

    public LiveData<Selectable> getSelected() {
        return selected;
    }

    public LiveData<List<Place>> getAllPlaces() {
        return allPlaces;
    }

    public LiveData<List<PlaceGroupWithPlaces>> getAllPlaceGroups() {
        return allPlaceGroups;
    }
}