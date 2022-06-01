package com.company.geonotifier.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.company.geonotifier.model.Place;
import com.company.geonotifier.model.PlaceGroupPlaceCrossRef;
import com.company.geonotifier.model.PlaceGroupWithPlaces;
import com.company.geonotifier.persistence.AppDatabase;
import com.company.geonotifier.persistence.PlaceGroupDao;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PlaceGroupRepository {

    private AppDatabase database;

    private PlaceGroupDao placeGroupDao;
    private LiveData<List<PlaceGroupWithPlaces>> allPlaceGroupsWithPlaces;

    private Executor executor;

    public PlaceGroupRepository(Application application) {
        database = AppDatabase.getInstance(application);
        executor = Executors.newSingleThreadExecutor();

        placeGroupDao = database.placeGroupDao();

        allPlaceGroupsWithPlaces = placeGroupDao.getAllPlaceGroupsWithPlaces();
    }

    public void insert(PlaceGroupWithPlaces placeGroupWithPlaces) {
        executor.execute(() ->
                database.runInTransaction(() -> {
                    long placeGroupId = placeGroupDao.insert(placeGroupWithPlaces.getPlaceGroup());
                    for (Place place : placeGroupWithPlaces.getPlaces()) {
                        placeGroupDao.insert(new PlaceGroupPlaceCrossRef(place.getPlaceId(), placeGroupId));
                    }
                })
        );
    }

    public void update(PlaceGroupWithPlaces placeGroupWithPlaces) {
        executor.execute(() ->
                database.runInTransaction(() -> {
                    placeGroupDao.deletePlaceGroupRefs(placeGroupWithPlaces.getPlaceGroup().getPlaceGroupId());
                    placeGroupDao.update(placeGroupWithPlaces.getPlaceGroup());
                    for (Place place : placeGroupWithPlaces.getPlaces()) {
                        placeGroupDao.insert(new PlaceGroupPlaceCrossRef(place.getPlaceId(),
                                placeGroupWithPlaces.getPlaceGroup().getPlaceGroupId()));
                    }
                })
        );
    }

    public void delete(PlaceGroupWithPlaces placeGroupWithPlaces) {
        executor.execute(() ->
                database.runInTransaction(() -> {
                    placeGroupDao.delete(placeGroupWithPlaces.getPlaceGroup());
                    placeGroupDao.deletePlaceGroupRefs(placeGroupWithPlaces.getPlaceGroup().getPlaceGroupId());
                })
        );
    }

    public void deleteAll() {
        executor.execute(() ->
                database.runInTransaction(() -> {
                    placeGroupDao.deleteAllPlaceGroups();
                    placeGroupDao.deleteAllPlaceGroupRefs();
                })
        );
    }

    public LiveData<List<PlaceGroupWithPlaces>> getAll() {
        return allPlaceGroupsWithPlaces;
    }
}