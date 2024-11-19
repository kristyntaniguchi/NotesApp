package com.example.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//This class runs when the user wants to add a new note.
public class AddNoteActivity extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
    private EditText titleEdit, textEdit;
    private TextView dateModified;
    private Button addNoteBtn, readNotesBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        //Initialize variables
        titleEdit = findViewById(R.id.idEdtTitle);
        textEdit = findViewById(R.id.idEdtText);
        dateModified = findViewById(R.id.idTvDateModified);
        addNoteBtn= findViewById(R.id.idBtnAddNote);
        readNotesBtn = findViewById(R.id.idBtnReadAllNotes);


        //Pass context to DBHandler
        dbHandler = new DBHandler(AddNoteActivity.this);

        //Click listener for addNoteBtn
        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get input from text edits
                String noteTitle = titleEdit.getText().toString();
                String noteText = textEdit.getText().toString();

                //Make sure there's input in at least one of the fields
                if (noteTitle.isEmpty() || noteText.isEmpty()) {
                    Toast.makeText(AddNoteActivity.this, "Please enter a title or text for the note.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Update current date and time
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy hh:mm a",
                        Locale.getDefault());
                String currentDateTime = dateFormat.format(new Date());
                dateModified.setText(currentDateTime);

                //Call addNewNote method from DBHandler and pass the input
                dbHandler.addNewNote(noteTitle, noteText, currentDateTime);
            }
        });//End of addNoteBtn.setOnClickListener()


        //Saves new note and opens a new activity to view all notes
        readNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get input from text edits
                String noteTitle = titleEdit.getText().toString();
                String noteText = textEdit.getText().toString();

                //If there's nothing in the note, open the ViewNotes activity without adding a new
                // note
                if (noteTitle.isEmpty() && noteText.isEmpty()) {
                    Intent i = new Intent(AddNoteActivity.this, MainActivity.class);
                    startActivity(i);
                }else{

                    //Update current date and time
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy hh:mm a",
                            Locale.getDefault());
                    String currentDateTime = dateFormat.format(new Date());
                    dateModified.setText(currentDateTime);

                    //Call addNewNote method from DBHandler and pass the input
                    dbHandler.addNewNote(noteTitle, noteText, currentDateTime);

                    //Open a new activity with intent.  Want to move from MainActivity to ViewNotes
                    Intent i = new Intent(AddNoteActivity.this, MainActivity.class);
                    startActivity(i);
                }

            }
        });//End of readNotesBtn.setOnClickListener()


    }//End of onCreate()
}
