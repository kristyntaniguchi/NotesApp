package com.example.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UpdateNoteActivity extends AppCompatActivity {
    //Create variables for our edit text, button and dbhandler.
    private EditText titleEdit, textEdit;
    private TextView dateModifiedText;
    private Button updateNoteBtn, readAllNotesBtn;
    private ImageButton deleteNoteBtn;
    private DBHandler dbHandler;
    //Create strings for the updated title, text, and date of the note.
    String updatedNoteTitle, updatedNoteText, updatedNoteDateModified;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);

        //Initialize variables for the buttons, text views, and edits.
        titleEdit = findViewById(R.id.idEdtTitleUpdate);
        textEdit = findViewById(R.id.idEdtTextUpdate);
        dateModifiedText = findViewById(R.id.idTvDateModifiedUpdate);
        //updateNoteBtn saves changes but leave the current note open.
        updateNoteBtn = findViewById(R.id.idBtnUpdateNote);
        //readAllNotesBtn saves changes and takes the user to the read all notes activity.
        readAllNotesBtn = findViewById(R.id.idBtnReadAllNotesUpdate);
        deleteNoteBtn = findViewById(R.id.idBtnDeleteNoteRv);

        //Initialize dbHandler
        dbHandler = new DBHandler(UpdateNoteActivity.this);

        //Get the updated data.  getStringExtra() matches putExtra() from RVAdapter
        updatedNoteTitle = getIntent().getStringExtra("title");
        updatedNoteText = getIntent().getStringExtra("text");
        updatedNoteDateModified = getIntent().getStringExtra("dateModified");

        //Update the EditTexts in the view with the updated data
        titleEdit.setText(updatedNoteTitle);
        textEdit.setText(updatedNoteText);
        dateModifiedText.setText(updatedNoteDateModified);

        //Add an onClickListener to the updateNoteBtn
        updateNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Update current date and time
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy hh:mm a",
                        Locale.getDefault());
                updatedNoteDateModified = dateFormat.format(new Date());
                dateModifiedText.setText(updatedNoteDateModified);

                //Update the note with the updatedNote values
                dbHandler.updateNote(updatedNoteTitle, titleEdit.getText().toString(),
                        textEdit.getText().toString(), dateModifiedText.getText().toString());

                //Display a toast message that the note has been updated
                Toast.makeText(UpdateNoteActivity.this, "Note Updated..", Toast.LENGTH_SHORT).show();

            }//End of onClick()

        });//End of updateNoteBtn.setOnClickListener()

        //Reads all notes
        readAllNotesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Save the changes and go to the read all notes activity.
                //Update current date and time
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy hh:mm a",
                        Locale.getDefault());
                updatedNoteDateModified = dateFormat.format(new Date());
                dateModifiedText.setText(updatedNoteDateModified);

                //Update the note with the updatedNote values
                dbHandler.updateNote(updatedNoteTitle, titleEdit.getText().toString(),
                        textEdit.getText().toString(), dateModifiedText.getText().toString());

                //Display a toast message that the note has been updated
                Toast.makeText(UpdateNoteActivity.this, "Note Updated..", Toast.LENGTH_SHORT).show();

                //Open a new activity with intent.
                //Want to move from the UpdateNoteActivity to the ViewNotes activity.
                Intent i = new Intent(UpdateNoteActivity.this, ViewNotes.class);
                startActivity(i);

            }
        });//End of readNotesBtn.setOnClickListener()

        //Deletes the current note
        deleteNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Call method to delete the note
                dbHandler.deleteNote(updatedNoteTitle);

                //Display a toast message that the note has been deleted
                Toast.makeText(UpdateNoteActivity.this, "Note Deleted..", Toast.LENGTH_SHORT).show();

                //Open a new activity with intent.
                //Want to move from the UpdateNoteActivity to the ViewNotes activity.
                Intent i = new Intent(UpdateNoteActivity.this, ViewNotes.class);
                startActivity(i);

            }
        });//End of deleteNoteBtn.setOnClickListener()


    }//End of onCreate()

}//End of UpdateNoteActivity class
