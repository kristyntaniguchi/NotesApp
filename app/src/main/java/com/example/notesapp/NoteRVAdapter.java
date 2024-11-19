package com.example.notesapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

//Adapter class for the recycler view
public class NoteRVAdapter extends RecyclerView.Adapter<NoteRVAdapter.ViewHolder> {

    private ArrayList<NotesModal> notesModalArrayList;
    private Context context;

    //Constructor
    public NoteRVAdapter( ArrayList<NotesModal> notes, Context context) {
        this.context = context;
        this.notesModalArrayList = notes;
    }//End of NoteRVAdapter()

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate the layout
        View view = LayoutInflater.from(context).inflate(R.layout.note_rv_item, parent, false);
        return new ViewHolder(view);
    }//End of onCreateViewHolder()

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Get the note at the position
        NotesModal note = notesModalArrayList.get(position);

        //Use try catch to handle parsing exceptions
        try {
            // Parse the original string to a Date
            SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM dd, yyyy hh:mm a", Locale.getDefault());
            Date date = inputFormat.parse(note.getDateModified());

            // Convert Date back to String in desired format
            SimpleDateFormat outputFormat = new SimpleDateFormat("MM/dd/yy", Locale.getDefault());
            String formattedDate = outputFormat.format(date);

            holder.dateModifiedTV.setText(formattedDate);
        } catch (ParseException e) {
            // If parsing fails, use original date string
            holder.dateModifiedTV.setText(note.getDateModified());
        }

        //Set the text of the text views
        holder.titleTV.setText(note.getNoteTitle());
        holder.textTV.setText(note.getNoteText());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create an intent
                Intent i = new Intent(context, UpdateNoteActivity.class);

                //Pass the values
                i.putExtra("title", note.getNoteTitle());
                i.putExtra("text", note.getNoteText());
                i.putExtra("dateModified", note.getDateModified());

                //Start the activity
                context.startActivity(i);
            }
        });
    }//End of onBindViewHolder()

    @Override
    public int getItemCount() {
        return notesModalArrayList.size();
    }//End of getItemCount()


    public static class ViewHolder extends RecyclerView.ViewHolder {
        //Initialize variables
        private TextView titleTV, textTV, dateModifiedTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Assign variables
            titleTV = itemView.findViewById(R.id.idTVTitleRv);
            textTV = itemView.findViewById(R.id.idTVTextRv);
            dateModifiedTV = itemView.findViewById(R.id.idTvDateModifiedRv);
        }//End of NoteViewHolder()
    }//End of NoteViewHolder class


}//End of class
