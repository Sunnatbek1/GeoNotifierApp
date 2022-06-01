package com.company.geonotifier.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.company.geonotifier.R;
import com.company.geonotifier.ui.settings.SettingsFormState;
import com.company.geonotifier.util.DevicePrefs;

import org.apache.commons.lang3.StringUtils;

import static com.company.geonotifier.ui.addeditplace.AddEditPlaceFragment.DEFAULT_RANGE;
import static com.company.geonotifier.ui.addeditplace.AddEditPlaceFragment.PREF_KEY_DEFAULT_RANGE;

public class SettingsFragmentViewModel extends AndroidViewModel {

    private MutableLiveData<SettingsFormState> settingsFormState = new MutableLiveData<>();

    public SettingsFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public void rangeValueChanged(String newRange) {
        if (isRangeValueValid(newRange)) {
            if (getSavedRange() == Integer.parseInt(newRange)) {
                settingsFormState.setValue(new SettingsFormState(false, true));
            } else {
                settingsFormState.setValue(new SettingsFormState(true, true));
            }
        } else {
            settingsFormState.setValue(new SettingsFormState(R.string.enter_valid_range));
        }
    }

    private boolean isRangeValueValid(String range) {
        return StringUtils.isNumeric(range) && range.length() < 5 &&
                Integer.parseInt(range) >= 10 && Integer.parseInt(range) <= 1000;
    }

    public int getSavedRange() {
        return DevicePrefs.getPrefs(getApplication(), PREF_KEY_DEFAULT_RANGE, DEFAULT_RANGE);
    }

    public LiveData<SettingsFormState> getSettingsFormState() {
        return settingsFormState;
    }
}
