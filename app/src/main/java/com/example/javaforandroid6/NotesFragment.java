package com.example.javaforandroid6;

import static com.example.javaforandroid6.R.drawable.rectangle;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotesFragment extends Fragment {

    private Note note = null;

    private static final String CURRENT_NOTE = "CurrentNote";
    private int notesCount = 1;
    private int currentPosition = 0;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /*public NotesFragment() {
        // Required empty public constructor
    }*/

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotesFragment newInstance(String param1, String param2) {
        NotesFragment fragment = new NotesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null) {
            /*currentPosition = savedInstanceState.getInt(CURRENT_NOTE, 0);*/
            note = (Note) savedInstanceState.getSerializable(CURRENT_NOTE);
        }

        initList(view);

        if (isLandscape()) {
            showLandNoteDescription(note);
        }
    }


    @SuppressLint({"ResourceAsColor", "SetTextI18n", "ResourceType"})
    private void initList(View view) {

        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");


        LinearLayout layoutView = (LinearLayout) view;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.FILL_PARENT);
        params.height = 6;
        params.width = 1200;

        //NoteStorage notesArray = new NoteStorage();
        Button btNewNote = view.findViewById(R.id.btNewNote);

        btNewNote.setOnClickListener(v -> {
            String noteName = "Note " + notesCount;
            note = new Note(noteName, formatter, notesCount);
            //notesArray.addNote(newNote);
            TextView tvNote = new TextView(getContext());
            tvNote.setText(notesCount + ". " + note.getNoteName());
            notesCount++;
            tvNote.setTextSize(30);
            tvNote.setTypeface(null, Typeface.BOLD_ITALIC);
            layoutView.addView(tvNote);
            View line2 = new View(getContext());
            line2.setBackgroundColor(Color.BLACK);
            line2.setLayoutParams(params);
            layoutView.addView(line2);

            tvNote.setOnClickListener(v1 -> {
                showNoteDescription(note);
            });



            /*Button btCreateNewNote = new Button(getContext());
            btCreateNewNote.setHint(R.string.enter_note_title);
            btCreateNewNote.setTextSize(25);
            btCreateNewNote.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
            btCreateNewNote.setTypeface(null, Typeface.BOLD_ITALIC);
            layoutView.addView(btCreateNewNote);

            View line2 = new View(getContext());
            line2.setBackgroundColor(Color.BLACK);
            line2.setLayoutParams(params);
            layoutView.addView(line2);
            notesCount++;*/
        });

/*        //String[] notes = getResources().getStringArray(R.array.notes);

        for (int i = 0; i < notes.length; i++) {
            TextView tvNote = new TextView(getContext());
            String note = notes[i];
            tvNote.setText(" " + (i + 1) + ". " + note);
            tvNote.setTextSize(30);
            tvNote.setTypeface(null, Typeface.BOLD_ITALIC);
            layoutView.addView(tvNote);
            final int position = i;
            tvNote.setOnClickListener(v -> {
                currentPosition = position;
                showNoteDescription(position);
            });
            ImageView line = new ImageView(getContext());
            line.setImageResource(rectangle);
            layoutView.addView(line);

            View line2 = new View(getContext());
            line2.setBackgroundColor(Color.BLACK);
            line2.setLayoutParams(params);
            layoutView.addView(line2);

        }*/
    }

    private void showNoteDescription(Note note) {

        if (isLandscape()) {
            showLandNoteDescription(note);
        } else {
            showPortNoteDescription(note);
        }


    }

    private void showPortNoteDescription(Note note) {

        NoteDescriptionFragment noteDescriptionFragment = NoteDescriptionFragment.newInstance(note);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer, noteDescriptionFragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();

    }

    private void showLandNoteDescription(Note note) {

        NoteDescriptionFragment landNoteDescriptionFragment = NoteDescriptionFragment.newInstance(note);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.noteDescriptionContainer, landNoteDescriptionFragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private boolean isLandscape() {
        return getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(CURRENT_NOTE, note);
        super.onSaveInstanceState(outState);

    }
}