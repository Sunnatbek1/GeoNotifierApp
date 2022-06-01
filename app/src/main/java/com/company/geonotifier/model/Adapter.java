package com.company.geonotifier.model;

public class Adapter {

    public static NoteDto toNote(Note note) {
        NoteDto dto = new NoteDto();
        dto.setNoteId(note.getNoteId());
        dto.setBody(note.getBody());
        dto.setTitle(note.getTitle());
        return dto;
    }

    public static PlaceDto toPlace(Place place) {
        PlaceDto placeDto = new PlaceDto();
        placeDto.setPlaceId(place.getPlaceId());
        placeDto.setLatitude(placeDto.getLatitude());
        placeDto.setLongitude(placeDto.getLongitude());
        placeDto.setName(placeDto.getName());
        placeDto.setRadius(placeDto.getRadius());
        return placeDto;
    }

    public static ReminderDto toReminder(Reminder reminder) {
        ReminderDto reminderDto = new ReminderDto();
        reminderDto.setReminderId(reminder.getReminderId());
        reminderDto.setPlaceGroupId(reminderDto.getPlaceGroupId());
        reminderDto.setNoteId(reminderDto.getNoteId());
        reminderDto.setPlaceId(reminderDto.getPlaceId());
        return reminderDto;
    }
}
