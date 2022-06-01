package com.company.geonotifier.model;

public class ReminderWithAllDto {

   private ReminderDto reminder;

   private NoteDto note;

   private PlaceDto place;

   private PlaceGroupWithPlacesDto groups;

    public ReminderDto getReminder() {
        return reminder;
    }

    public void setReminder(ReminderDto reminder) {
        this.reminder = reminder;
    }

    public NoteDto getNote() {
        return note;
    }

    public void setNote(NoteDto note) {
        this.note = note;
    }

    public PlaceDto getPlace() {
        return place;
    }

    public void setPlace(PlaceDto place) {
        this.place = place;
    }

    public PlaceGroupWithPlacesDto getGroups() {
        return groups;
    }

    public void setGroups(PlaceGroupWithPlacesDto groups) {
        this.groups = groups;
    }
}
