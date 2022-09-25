package com.example.javaforandroid6;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoteDescriptionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoteDescriptionFragment extends Fragment {


    static final String SELECTED_NOTE = "note";
    private Note note;

    public NoteDescriptionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (savedInstanceState != null)
            requireActivity().getSupportFragmentManager().popBackStack();*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_note_description, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //setActionBar(view);
        Bundle arguments = getArguments();

        Button buttonBack = view.findViewById(R.id.btSave);
        buttonBack.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        if (arguments != null) {

            //int index = arguments.getInt(SELECTED_NOTE);
            Note paramNote = (Note) arguments.getParcelable(SELECTED_NOTE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                note = Arrays.stream(Note.getNotes()).filter(n -> n.getId() == paramNote.getId()).findFirst().get();
            }


            EditText edNoteName = view.findViewById(R.id.edNoteName);
            EditText edNoteDescription = view.findViewById(R.id.edNoteDescription);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                edNoteName.setText(note.getNoteName());
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                edNoteDescription.setText(note.getNoteDescription());
            }
            edNoteName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    note.setNoteName(edNoteName.getText().toString());
                    updateData();
                    //Note.getNotes()[index].setTitle(charSequence.toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });

            edNoteDescription.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    note.setNoteDescription(edNoteDescription.getText().toString());
                    updateData();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateData() {
        NotesFragment notesFragment = (NotesFragment) requireActivity().getSupportFragmentManager().getFragments().stream().filter(fragment -> fragment instanceof NotesFragment)
                .findFirst().get();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notesFragment.initNotes();
        }

    }

    public static NoteDescriptionFragment newInstance(int index) {
        NoteDescriptionFragment fragment = new NoteDescriptionFragment();
        Bundle args = new Bundle();
        args.putInt(SELECTED_NOTE, index);
        fragment.setArguments(args);
        return fragment;
    }

    public static NoteDescriptionFragment newInstance(Note note) {
        NoteDescriptionFragment fragment = new NoteDescriptionFragment();
        Bundle args = new Bundle();
        args.putParcelable(SELECTED_NOTE, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_menu, menu);
        MenuItem itemSearch = menu.findItem(R.id.actionSearch);
        MenuItem itemSort = menu.findItem(R.id.actionSort);

        if (itemSearch != null) {
            itemSearch.setVisible(false);
        }

        if (itemSort != null) {
            itemSort.setVisible(false);
        }


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.actionAddPhoto){

            return true;
        }

        if (item.getItemId() == R.id.actionShare){

            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    /*    private void setActionBar(@NonNull View view) {
        Toolbar toolbar = view.findViewById(R.id.noteToolbar);
        toolbar.setSubtitle("Note description");
        ((AppCompatActivity) requireActivity()).setActionBar(toolbar);
        setHasOptionsMenu(true);
    }*/
}