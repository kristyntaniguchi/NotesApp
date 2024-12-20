package com.example.notesapp;

//NotesModal is the model for the notes object.
public class NotesModal {
    //Declare variables
    private String noteTitle;
    private String noteText;
    private String dateModified;
    private int id;

    //Getters and setters
    public String getNoteTitle() {return noteTitle;}
    public void setNoteTitle(String noteTitle) {this.noteTitle = noteTitle;}

    public String getNoteText() {return noteText;}
    public void setNoteText(String noteText) {this.noteText = noteText;}

    public String getDateModified() {return dateModified;}
    public void setDateModified(String dateModified) {this.dateModified = dateModified;}

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    //Constructor
    public NotesModal(String noteTitle, String noteText, String dateModified) {
        this.noteTitle = noteTitle;
        this.noteText = noteText;
        this.dateModified = dateModified;
    }

}
