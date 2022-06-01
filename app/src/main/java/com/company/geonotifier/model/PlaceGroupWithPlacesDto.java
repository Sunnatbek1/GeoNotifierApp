package com.company.geonotifier.model;

import java.util.List;

public class PlaceGroupWithPlacesDto {
    private List<PlaceDto> places;

    public List<PlaceDto> getPlaces() {
        return places;
    }

    public void setPlaces(List<PlaceDto> places) {
        this.places = places;
    }
}
