package com.example.javaforandroid6;

/**
 * Created by Viktor-Ruff
 * Date: 11.08.2022
 * Time: 21:48
 */
public interface NotesStorageImpl {

    void addNote(Note note);

    void deleteNote(int index);

    NoteStorage getArrayNotes();
}
