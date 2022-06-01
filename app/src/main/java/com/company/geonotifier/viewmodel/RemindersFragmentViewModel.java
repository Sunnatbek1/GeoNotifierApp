package com.company.geonotifier.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.company.geonotifier.model.ReminderWithNotePlacePlaceGroup;
import com.company.geonotifier.repository.ReminderRepository;

import java.util.List;

public class RemindersFragmentViewModel extends AndroidViewModel {

    private ReminderRepository repository;
    private LiveData<List<ReminderWithNotePlacePlaceGroup>> allReminders;

    public RemindersFragmentViewModel(@NonNull Application application) {
        super(application);
        repository = new ReminderRepository(application);
        allReminders = repository.getAllRemindersWithNotePlacePlaceGroup();
    }

    public void insert(ReminderWithNotePlacePlaceGroup reminder) {
        repository.insert(reminder);
    }

    public void delete(ReminderWithNotePlacePlaceGroup reminder) {
        repository.delete(reminder);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public LiveData<List<ReminderWithNotePlacePlaceGroup>> getAllReminders() {
        return allReminders;
    }
}
