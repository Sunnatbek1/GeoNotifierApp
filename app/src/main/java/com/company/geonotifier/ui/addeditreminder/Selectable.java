package com.company.geonotifier.ui.addeditreminder;


public interface Selectable {

    int PLACE = 0;
    int PLACE_GROUP = 1;

    Long getId();

    String getDisplayText();

    int getDisplayIcon();

    int getType();
}
