package com.company.geonotifier.ui.addeditnote;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.company.geonotifier.R;
import com.company.geonotifier.model.Note;
import com.company.geonotifier.ui.AddEditFragment;
import com.company.geonotifier.ui.MainActivity;
import com.company.geonotifier.ui.addeditreminder.AddEditReminderFragment;
import com.company.geonotifier.util.ConfigUtils;
import com.company.geonotifier.viewmodel.AddEditNoteFragmentViewModel;

public class AddEditNoteFragment extends AddEditFragment {

    public static final String BUNDLE_KEY_NOTE = "com.company.geonotifier.BUNDLE_KEY_NOTE";

    private EditText titleEditText;
    private EditText noteEditText;

    private Note currentNote;

    private AddEditNoteFragmentViewModel viewModel;

    @Override
    protected View inflateFragment(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_edit_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        initViewModel();
        retrieveNote();
    }

    private void initViews(View view) {
        titleEditText = view.findViewById(R.id.txt_title);
        noteEditText = view.findViewById(R.id.txt_note);
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(AddEditNoteFragmentViewModel.class);
    }

    private void retrieveNote() {
        if (getArguments() != null && getArguments().getParcelable(BUNDLE_KEY_NOTE) != null) {
            currentNote = getArguments().getParcelable(BUNDLE_KEY_NOTE);
            ((MainActivity) requireActivity()).getSupportActionBar().setTitle(getString(R.string.edit_note));
            titleEditText.setText(currentNote.getTitle());
            noteEditText.setText(currentNote.getBody());
        }
    }

    @Override
    protected void saveMenuItemClicked() {
        saveNote();
    }

    @Override
    protected void addToReminderMenuItemClicked() {
        addToReminder();
    }

    @Override
    protected void deleteItem() {
        deleteCurrentNote();
    }

    private void saveNote() {
        String noteTitle = titleEditText.getText().toString().trim();
        String noteBody = noteEditText.getText().toString().trim();

        if (noteBody.isEmpty() && noteTitle.isEmpty()) return;

        if (inEditMode()) updateCurrentNote(noteTitle, noteBody);
        else insertNewNote(noteTitle, noteBody);

        ConfigUtils.closeKeyboard(requireActivity());
        navController.popBackStack();
    }

    private void updateCurrentNote(String noteTitle, String noteBody) {
        currentNote.setTitle(noteTitle);
        currentNote.setBody(noteBody);

        viewModel.update(currentNote);
    }

    private void insertNewNote(String noteTitle, String noteBody) {
        Note newNote = new Note(noteTitle, noteBody);

        viewModel.insert(newNote);
    }

    private void deleteCurrentNote() {
        viewModel.delete(currentNote);
    }

    private void addToReminder() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(AddEditReminderFragment.BUNDLE_KEY_NOTE_RETRIEVED, currentNote);
        navController.navigate(R.id.action_addEditNoteFragment_to_addEditReminderFragment, bundle);
    }

    @Override
    protected boolean inEditMode() {
        return currentNote != null;
    }
}