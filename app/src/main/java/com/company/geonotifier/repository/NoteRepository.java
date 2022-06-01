package com.company.geonotifier.repository;

import android.app.Application;

import com.company.geonotifier.model.Note;
import com.company.geonotifier.persistence.AppDatabase;

public class NoteRepository extends BaseRepository<Note>{

    public NoteRepository(Application application) {
        super();
        AppDatabase database = AppDatabase.getInstance(application);
        dao = database.noteDao();
        allItems = dao.getAll();
    }
}
