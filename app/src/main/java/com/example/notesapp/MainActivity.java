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

public class MainActivity extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
    private EditText titleEdit, textEdit;
    private TextView dateModified;
    private Button addNoteBtn, readNoteBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize variables
        titleEdit = findViewById(R.id.idEdtTitle);
        textEdit = findViewById(R.id.idEdtText);
        dateModified = findViewById(R.id.idTvDateModified);
        addNoteBtn= findViewById(R.id.idBtnAddNote);
        readNoteBtn = findViewById(R.id.idBtnReadAllNotes);

        //Get current date and time
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy hh:mm a",
                Locale.getDefault());
        String currentDateTime = dateFormat.format(new Date());
        dateModified.setText(currentDateTime);

        //Pass context to DBHandler
        dbHandler = new DBHandler(MainActivity.this);

        //Click listener for addNoteBtn
        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get input from text edits
                String noteTitle = titleEdit.getText().toString();
                String noteText = textEdit.getText().toString();

                //Update current date and time
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy hh:mm a",
                        Locale.getDefault());
                String currentDateTime = dateFormat.format(new Date());
                dateModified.setText(currentDateTime);

                //Make sure there's input in at least one of the fields
                if (noteTitle.isEmpty() || noteText.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a title or text for the note.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Call addNewNote method from DBHandler and pass the input
                dbHandler.addNewNote(noteTitle, noteText);

                // after adding the data we are displaying a toast message.
                Toast.makeText(MainActivity.this, "Note has been added.", Toast.LENGTH_SHORT).show();
                titleEdit.setText("");
                textEdit.setText("");
            }
        });//End of addNoteBtn.setOnClickListener()


        readNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Open a new activity with intent.  ViewNotes in the Java class.
                Intent i = new Intent(MainActivity.this, ViewNotes.class);
                startActivity(i);
            }
        });//End of readNotesBtn.setOnClickListener()


    }//End of onCreate()
}
