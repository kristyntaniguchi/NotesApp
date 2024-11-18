package com.example.notesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//Adapter class for the recycler view
public class NoteRVAdapter extends RecyclerView.Adapter<NoteRVAdapter.NoteViewHolder> {

    private Context context;
    private ArrayList<NotesModal> notesModalArrayList;

    public NoteRVAdapter(Context context, ArrayList<NotesModal> notes) {
        this.context = context;
        this.notesModalArrayList = notes;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate the layout
        View view = LayoutInflater.from(context).inflate(R.layout.note_rv_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        //Get the note at the position
        NotesModal note = notesModalArrayList.get(position);

        //Set the text of the text views
        holder.title.setText(note.getNoteTitle());
        holder.text.setText(note.getNoteText());
        holder.dateModified.setText(note.getDateModified());
    }

    @Override
    public int getItemCount() {
        return notesModalArrayList.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {

        //Initialize variables
        TextView title, text, dateModified;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            //Assign variables
            title = itemView.findViewById(R.id.idTVTitleRv);
            text = itemView.findViewById(R.id.idTVTextRv);
            dateModified = itemView.findViewById(R.id.idTvDateModifiedRv);
        }
    }
}
