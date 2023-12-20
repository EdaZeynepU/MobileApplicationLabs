package com.example.weekx;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.weekx.NoteFragment.OnNoteListInteractionListener;
import com.example.weekx.placeholder.PlaceholderContent.PlaceholderItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyNoteRecyclerViewAdapter extends RecyclerView.Adapter<MyNoteRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Note> notes;
    private OnNoteListInteractionListener listener;

    public MyNoteRecyclerViewAdapter(ArrayList<Note> notes, OnNoteListInteractionListener listener) {
        this.notes = notes;
        this.listener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_note, parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = notes.get(position);
        holder.mHeaderView.setText(notes.get(position).getHeader());
        holder.mDataView.setText(new SimpleDateFormat().format(notes.get(position).getDate()));
        holder.mView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                listener.onNoteSelected(holder.mItem);
            }
        });

        if(position%2 == 1){
            holder.mView.setBackgroundColor(Color.YELLOW);
        }else{
            holder.mView.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mHeaderView;
        public final TextView mDataView;
        public final View mView;
        public Note mItem;

        public ViewHolder(View view) {
            super(view);
            mHeaderView = view.findViewById(R.id.note_header);
            mDataView = view.findViewById(R.id.note_header);
            mView=view;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mHeaderView.getText() + "'";
        }
    }
}