package com.example.javaforandroid6;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
    private static int counter;


    private int id;
    private String noteName;
    private String noteDescription;
    private LocalDateTime creationDate;


    public int getId() {
        return id;
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
        id = ++counter;
    }

    static {
        notes = new Note[10];
        for (int i = 0; i < notes.length; i++) {
            notes[i] = Note.getNote(i);
        }
    }

    public Note(String noteName, String noteDescription, LocalDateTime creationDate) {
        this.noteName = noteName;
        this.noteDescription = noteDescription;
        this.creationDate = creationDate;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("DefaultLocale")
    public static Note getNote(int index) {
        String noteName = String.format("%d. Заметка", (index + 1));
        String description = String.format("Описание заметки %d", index);
        LocalDateTime creationDate = LocalDateTime.now().plusDays(-random.nextInt(5));
        return new Note(noteName, description, creationDate);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    protected Note(Parcel parcel) {
        id = parcel.readInt();
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
        parcel.writeInt(getId());
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
}
