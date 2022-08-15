package com.example.javaforandroid6;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Viktor-Ruff
 * Date: 11.08.2022
 * Time: 21:40
 */
public class NoteStorage implements NotesStorageImpl, Serializable {

    ArrayList<Note> notesArray;


    public NoteStorage() {
        this.notesArray = new ArrayList<>(1);
    }


    @Override
    public void addNote(Note note) {
        this.notesArray.add(note);
    }


    @Override
    public void deleteNote(int index) {
        notesArray.remove(index);
    }


    @Override
    public NoteStorage getArrayNotes() {
        return this;
    }


}
