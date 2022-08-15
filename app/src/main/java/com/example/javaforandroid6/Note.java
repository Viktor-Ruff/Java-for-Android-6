package com.example.javaforandroid6;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * Created by Viktor-Ruff
 * Date: 14.08.2022
 * Time: 21:42
 */
public class Note implements Serializable {

    private int index;
    private String noteName;
    private String noteDescription;
    private SimpleDateFormat noteInformation;

    public Note(String noteName, SimpleDateFormat noteInformation, int index) {
        this.noteName = noteName;
        this.index = index;
        this.noteInformation = noteInformation;
    }


    public String getNoteName() {
        return noteName;
    }


    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }


    public String getNoteDescription() {
        return noteDescription;
    }


    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }


    public SimpleDateFormat getNoteInformation() {
        return noteInformation;
    }


    public void setNoteInformation(SimpleDateFormat noteInformation) {
        this.noteInformation = noteInformation;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
