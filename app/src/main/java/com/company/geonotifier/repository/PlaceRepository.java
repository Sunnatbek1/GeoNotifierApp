package com.company.geonotifier.repository;

import android.app.Application;

import com.company.geonotifier.model.Place;
import com.company.geonotifier.persistence.AppDatabase;

public class PlaceRepository extends BaseRepository<Place> {

    public PlaceRepository(Application application) {
        super();
        AppDatabase database = AppDatabase.getInstance(application);
        dao = database.placeDao();
        allItems = dao.getAll();
    }
}
