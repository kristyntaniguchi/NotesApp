package com.example.notesapp;

//This class displays all of the notes in the database.
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
    public NotesModal(String noteTitle, String noteText, String dateCreated) {
        this.noteTitle = noteTitle;
        this.noteText = noteText;
        this.dateModified = dateModified;
    }

}
