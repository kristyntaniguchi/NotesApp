package com.example.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //Create variables
    private ArrayList<NotesModal> notesModalArrayList;
    private DBHandler dbHandler;
    private NoteRVAdapter noteRVAdapter;
    private RecyclerView notesRV;
    private ImageButton addNoteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize variables
        notesRV = findViewById(R.id.idRvNotes);
        dbHandler = new DBHandler(MainActivity.this);
        addNoteBtn = findViewById(R.id.idBtnAddNewNoteVN);

        //Get the array list from dbHandler
        notesModalArrayList = dbHandler.readNotes();

        //Pass the array list to the adapter
        noteRVAdapter = new NoteRVAdapter( notesModalArrayList, MainActivity.this);

        //Set layout manager to recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        notesRV.setLayoutManager(linearLayoutManager);

        //Set adapter to recycler view
        notesRV.setAdapter(noteRVAdapter);

        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Start AddNote activity
                //Open a new activity with intent.  Want to move from MainActivity to ViewNotes
                Intent i = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(i);
            }
        });
    }//End of onCreate()


}
