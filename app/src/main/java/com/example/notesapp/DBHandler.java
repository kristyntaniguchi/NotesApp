package com.example.notesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

//DBHandler is a class that extends SQLiteOpenHelper and is used to create the database and tables.
public class DBHandler extends SQLiteOpenHelper {

    //Database name
    private static final String DB_NAME = "notesDb";
    //DB version
    private static final int DB_VERSION = 1;
    //Table name
    private static final String TABLE_NAME = "myNotes";

    //Columns
    //Id
    private static final String ID_COL = "id";
    //Note title
    private static final String TITLE_COL = "title";
    //Note text
    private static final String TEXT_COL = "text";
    //Date last updated
    private static final String DATE_MODIFIED_COL = "dateModified";


    //Constructor
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }//End of Constructor

    //Create the db
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Set column names with sqlite query
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE_COL + " TEXT,"
                + TEXT_COL + " TEXT,"
                + DATE_MODIFIED_COL + " TEXT)";

        //Execute the sql query
        db.execSQL(query);
    }//End of onCreate()


    //Add a new note. Date created and date modified are set automatically and don't need to be
    // passed in.
    public void addNewNote(String title, String text, String dateModified) {
        //Create db variable
        SQLiteDatabase db = this.getWritableDatabase();

        //Create content values
        ContentValues values = new ContentValues();

        //Pass values with their key and value pair
        values.put(TITLE_COL, title);
        values.put(TEXT_COL, text);
        values.put(DATE_MODIFIED_COL, dateModified);

        //Insert the values into the table
        db.insert(TABLE_NAME, null, values);

        //Close the database
        db.close();
    }//End of addNewNote()

    //Read all of the notes saved in the db
    public ArrayList<NotesModal> readNotes() {
        //Create the database to read from
        SQLiteDatabase db = this.getReadableDatabase();

        //Create cursor to read from the database
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        //Create an array list to store the notes
        ArrayList<NotesModal> notesModalArrayList = new ArrayList<>();

        //Move cursor to the first position
        if (cursor.moveToFirst()) {
            do {
                //Create a new notes modal object and add in the input
                notesModalArrayList.add(new NotesModal(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)));

            }
            while (cursor.moveToNext());
        }
        //Close the cursor and return the array list
        cursor.close();
        return notesModalArrayList;
    }//End of ArrayList<NotesModal> readNotes()

    //Update a note
    public void updateNote(String originalTitle, String noteTitle, String noteText,
                             String dateModified) {
        //Create a database to write to
        SQLiteDatabase db = this.getWritableDatabase();

        //Create content values
        ContentValues values = new ContentValues();

        //Pass values with their key and value pair
        values.put(TITLE_COL, noteTitle);
        values.put(TEXT_COL, noteText);
        values.put(DATE_MODIFIED_COL, dateModified);

        //Update the values in the table
        db.update(TABLE_NAME, values, TITLE_COL + " = ?", new String[]{originalTitle});
        //Close the database
        db.close();
    }//End of updateCourse()

    //Delete a note from the database
    public void deleteNote (String noteTitle) {
        //Create a database to write to
        SQLiteDatabase db = this.getWritableDatabase();

        //Delete the note from the table
        db.delete(TABLE_NAME, TITLE_COL + " = ?", new String[]{noteTitle});

        //Close the database
        db.close();
    }//End of deleteNote()

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }//End of onUpgrade()


}
