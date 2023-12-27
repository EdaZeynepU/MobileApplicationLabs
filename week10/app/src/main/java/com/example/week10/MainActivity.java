package com.example.week10;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements NoteFragment.OnNoteListInteractionListener {

    boolean displayingEditor = false;
    Note editingNote;
    ArrayList<Note> notes = new ArrayList<>();

    ListenerRegistration listenerRegistration;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!displayingEditor){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.container,NoteFragment.newInstance(),"list_note");
            ft.commit();
        } else{
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            ft.replace(R.id.container,EditNoteFragment.newInstance(editingNote.getContent()));
            ft.addToBackStack(null);
            ft.commit();
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        listenerRegistration = db.collection("notes").orderBy("data", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Log.d("Firebase","Error Retrieving");
                        }
                        for(QueryDocumentSnapshot doc:value){
                            notes.add(doc.toObject(Note.class));
                        }
                        NoteFragment listFragment = (NoteFragment) getSupportFragmentManager().findFragmentByTag("list_note");
                        listFragment.updateNotes(notes);
                    }
                });

    }

    public void onNoteSelected(Note note) {
        editingNote =note;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.container,EditNoteFragment.newInstance(editingNote.getContent()),"edit_note");
                ft.addToBackStack(null);
        ft.commit();
        displayingEditor = !displayingEditor;
        invalidateOptionsMenu();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("onOptionsItemSelected", item.getTitle().toString());
        displayingEditor = !displayingEditor;
        invalidateOptionsMenu();
        int itemId = item.getItemId();
        if (itemId == R.id.action_new) {
            editingNote = createNote();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, EditNoteFragment.newInstance(""), "edit_note");
            ft.addToBackStack(null);
            ft.commit();
            return true;
        } else if (itemId == R.id.action_close) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
//        switch (item.getItemId()) {
//            case R.id.action_new:
//                editingNote = createNote();
//                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                ft.replace(R.id.container,EditNoteFragment.newInstance(""),"edit_note");
//                ft.addToBackStack(null);
//                ft.commit();
//                return true;
//            case R.id.action_close:
//                onBackPressed();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }//TODO: if else çevir
    }

    private Note createNote() {
        Note note = new Note();
        String id = FirebaseFirestore.getInstance().collection("notes").document().getId();
        note.setId(id);
        return note;
    }

    private void saveContent(Note editingNote, String content){
        if (editingNote.getContent() == null || !editingNote.getContent().equals(content)) {
            editingNote.setContent(content);
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            editingNote.setDate(new Timestamp(new Date()));
            db.collection("notes").document(editingNote.getId()).set(editingNote);
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        listenerRegistration.remove();
    }

}