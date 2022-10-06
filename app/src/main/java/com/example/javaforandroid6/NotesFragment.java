package com.example.javaforandroid6;

import android.content.res.Configuration;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;

import static com.example.javaforandroid6.NoteDescriptionFragment.SELECTED_NOTE;

import android.widget.TextView;


import java.util.Random;


public class NotesFragment extends Fragment {

    Note note;
    View dataContainer;

    public NotesFragment() {
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(SELECTED_NOTE, note);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            note = (Note) savedInstanceState.getParcelable(SELECTED_NOTE);
        }

        dataContainer = view.findViewById(R.id.noteContainer);
        initNotes(dataContainer);

        if (isLandscape()) {
            //showLandNoteDetails(note);
        }
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void initNotes() {
        initNotes(dataContainer);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initNotes(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        layoutView.removeAllViews();
        for (int i = 0; i < Note.getNotes().length; i++) {

            TextView tv = new TextView(getContext());
            tv.setText(Note.getNotes()[i].getNoteName());
            tv.setTextSize(24);
            layoutView.addView(tv);

            final int index = i;
            tv.setOnClickListener(v -> {
                showNoteDetails(Note.getNotes()[index]);
            });
        }
    }

    private void showNoteDetails(Note note) {
        this.note = note;
        if (isLandscape()) {
            //showLandNoteDetails(note);
        } else {
            showPortNoteDetails(note);
        }
    }


    private void showPortNoteDetails(Note note) {


        NoteDescriptionFragment noteFragment = NoteDescriptionFragment.newInstance(note);
        FragmentManager fragmentManager =
                requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer, noteFragment); // замена  фрагмента
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

/*    private void showLandNoteDetails(Note note) {
        NoteDescriptionFragment noteDescriptionFragment = NoteDescriptionFragment.newInstance(note);
        FragmentManager fragmentManager =
                requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.noteDescriptionContainer, noteDescriptionFragment); // замена  фрагмента
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }


    private void showLandNoteDetails(int index) {
        NoteDescriptionFragment noteFragment = NoteDescriptionFragment.newInstance(index);
        FragmentManager fragmentManager =
                requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.noteDescriptionContainer, noteFragment); // замена  фрагмента
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }*/


}