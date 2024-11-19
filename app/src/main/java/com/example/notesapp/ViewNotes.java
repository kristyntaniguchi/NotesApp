package com.example.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewNotes extends AppCompatActivity {
    //Create variables
    private ArrayList<NotesModal> notesModalArrayList;
    private DBHandler dbHandler;
    private NoteRVAdapter noteRVAdapter;
    private RecyclerView notesRV;
    private ImageButton addNoteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);

        //Initialize variables
        notesRV = findViewById(R.id.idRvNotes);
        dbHandler = new DBHandler(ViewNotes.this);
        addNoteBtn = findViewById(R.id.idBtnAddNewNoteVN);

        //Get the array list from dbHandler
        notesModalArrayList = dbHandler.readNotes();

        //Pass the array list to the adapter
        noteRVAdapter = new NoteRVAdapter( notesModalArrayList, ViewNotes.this);

        //Set layout manager to recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewNotes.this);
        notesRV.setLayoutManager(linearLayoutManager);

        //Set adapter to recycler view
        notesRV.setAdapter(noteRVAdapter);

        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start AddNote activity
                //Open a new activity with intent.  Want to move from MainActivity to ViewNotes
                Intent i = new Intent(ViewNotes.this, MainActivity.class);
                startActivity(i);
            }
        });
    }//End of onCreate()


}
