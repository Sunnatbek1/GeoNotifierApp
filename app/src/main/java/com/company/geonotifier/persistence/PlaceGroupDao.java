package com.company.geonotifier.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.company.geonotifier.model.PlaceGroup;
import com.company.geonotifier.model.PlaceGroupPlaceCrossRef;
import com.company.geonotifier.model.PlaceGroupWithPlaces;

import java.util.List;

@Dao
public interface PlaceGroupDao {

    @Insert
    long insert(PlaceGroup placeGroup);

    @Insert
    void insert(PlaceGroupPlaceCrossRef placeGroupPlaceCrossRef);

    @Update
    void update(PlaceGroup placeGroup);

    @Delete
    void delete(PlaceGroup placeGroup);

    @Query("DELETE FROM PlaceGroupPlaceCrossRef WHERE placeGroupId = :placeGroupId")
    void deletePlaceGroupRefs(long placeGroupId);

    @Query("DELETE FROM PlaceGroup")
    void deleteAllPlaceGroups();

    @Query("DELETE FROM PlaceGroupPlaceCrossRef")
    void deleteAllPlaceGroupRefs();

    @Transaction
    @Query("SELECT * FROM PlaceGroup ORDER BY placeGroupId DESC")
    LiveData<List<PlaceGroupWithPlaces>> getAllPlaceGroupsWithPlaces();
}