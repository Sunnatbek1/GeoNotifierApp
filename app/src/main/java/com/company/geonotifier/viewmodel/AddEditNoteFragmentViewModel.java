package com.company.geonotifier.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.company.geonotifier.model.Note;
import com.company.geonotifier.repository.NoteRepository;

public class AddEditNoteFragmentViewModel extends BaseViewModel<Note> {

    public AddEditNoteFragmentViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allItems = repository.getAll();
    }
}
