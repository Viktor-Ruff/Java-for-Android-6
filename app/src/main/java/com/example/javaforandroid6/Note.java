package com.example.javaforandroid6;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Viktor-Ruff
 * Date: 14.08.2022
 * Time: 21:42
 */


@RequiresApi(api = Build.VERSION_CODES.O)
public class Note implements Parcelable {


    private static final Random random = new Random();
    private static Note[] notes;
    /*private static int counter;*/


    private int noteId;
    private String noteName;
    private String noteDescription;
    private LocalDateTime creationDate;


    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public static Note[] getNotes() {
        return notes;
    }

    public String getNoteName() {
        return noteName;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    {
        /*  noteId = ++counter;*/
    }

    static {
        notes = new Note[3];
        for (int i = 0; i < notes.length; i++) {
            notes[i] = Note.createNote(i);
            notes[i].setNoteId(i);
        }
    }

    public Note(String noteName, String noteDescription, LocalDateTime creationDate/*, int id*/) {
        /*this.noteId = id;*/
        this.noteName = noteName;
        this.noteDescription = noteDescription;
        this.creationDate = creationDate;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("DefaultLocale")
    public static Note createNote(int index) {
        String noteName = String.format("%d. Заметка", (index + 1));
        String description = String.format("Описание заметки %d", (index + 1));
        LocalDateTime creationDate = LocalDateTime.now().plusDays(-random.nextInt(5));
        /*  int noteId = index;*/
        return new Note(noteName, description, creationDate/*, noteId*/);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    protected Note(Parcel parcel) {
        noteId = parcel.readInt();
        noteName = parcel.readString();
        noteDescription = parcel.readString();
        creationDate = (LocalDateTime) parcel.readSerializable();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(getNoteId());
        parcel.writeString(getNoteName());
        parcel.writeString(getNoteDescription());
        parcel.writeSerializable(getCreationDate());
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public void remove(int id) {

        List<Note> list = new ArrayList<>(Arrays.asList(notes));
        list.remove(id);

        notes = list.toArray(new Note[list.size()]);

        for (int i = 0; i < notes.length; i++) {
            notes[i].setNoteId(i);
        }
    }

    public void addNote() {

        List<Note> list = new ArrayList<>(Arrays.asList(notes));
        Note newNote = createNote(list.size());
        newNote.setNoteId(list.size() - 1);
        list.add(newNote);

        notes = list.toArray(new Note[list.size()]);
    }

}
