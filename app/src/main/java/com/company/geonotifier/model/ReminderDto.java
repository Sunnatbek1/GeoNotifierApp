package com.company.geonotifier.model;

public class ReminderDto {

    private Long reminderId;
    private Long noteId;
    private Long placeId;
    private Long placeGroupId;
    private Boolean isActive;

    public Long getReminderId() {
        return reminderId;
    }

    public void setReminderId(Long reminderId) {
        this.reminderId = reminderId;
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public Long getPlaceGroupId() {
        return placeGroupId;
    }

    public void setPlaceGroupId(Long placeGroupId) {
        this.placeGroupId = placeGroupId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
