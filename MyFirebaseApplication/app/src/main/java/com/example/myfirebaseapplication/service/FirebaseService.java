package com.example.myfirebaseapplication.service;

import android.widget.ArrayAdapter;

import com.example.myfirebaseapplication.adapter.MyAdapter;
import com.example.myfirebaseapplication.model.Note;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.*;

public class FirebaseService {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String colNotes = "notes";
    //public List<Note> notes = new ArrayList<>();
    private ArrayAdapter adapter;

    public FirebaseService(ArrayAdapter adapter) {
        this.adapter = adapter;
    }

    public FirebaseService() {
    }

    public void startListener() {
        db.collection(colNotes).addSnapshotListener((snap, error) -> {
            if (error == null) {
                adapter.clear();
                for (DocumentSnapshot s : snap.getDocuments()) {
                    System.out.println(s.getData().get("text"));
                    String t = s.getData().get("text").toString();
                    Note note = new Note(s.getId(), t);
                    adapter.add(note);
                }
                // adapter.notifyDatasetChanged(); // Will update the gui
                adapter.notifyDataSetChanged();
            }
        });
    }

    // Add Doc
    public void addNote(String text) {
        DocumentReference ref = db.collection("notes").document();
        Map<String, String> map = new HashMap<>();
        map.put("text", text);
        ref.set(map);
    }

    public void add2Note(String text) {
        DocumentReference ref = db.collection("notes").document();
        Map<String, String> map = new HashMap<>();
        map.put("text", text);
        ref.set(map)
                .addOnSuccessListener(unused -> System.out.println("Document saved, " + text))
                .addOnFailureListener(e -> System.out.println("Document Not saved, " + text));

    }


    public void updateNote(String documentId, String newText) {
        DocumentReference ref = db.collection("notes").document(documentId);
        Map<String, Object> updates = new HashMap<>();
        updates.put("text", newText);
        ref.update(updates)
                .addOnSuccessListener(unused -> System.out.println("Document updated, " + documentId))
                .addOnFailureListener(e -> System.out.println("Document not updated, " + documentId));
    }

    /* Images to/from notes */
    public void addImageToNote(String documentId, String imageName, String imageURL) {
        DocumentReference ref = db.collection("notes").document(documentId);
        Map<String, Object> updates = new HashMap<>();
        updates.put("imageName", imageName);
        updates.put("imageURL", imageURL);
        ref.update(updates)
                .addOnSuccessListener(unused -> System.out.println("Document updated, " + documentId))
                .addOnFailureListener(e -> System.out.println("Document not updated, " + documentId));
    }

    public void getImageFromNote(String documentId, OnSuccessListener<String> successListener, OnFailureListener failureListener) {
        DocumentReference ref = db.collection("notes").document(documentId);
        ref.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                String imageURL = documentSnapshot.getString("imageURL");
                successListener.onSuccess(imageURL);
            } else {
                failureListener.onFailure(new Exception("Document does not exist"));
            }
        }).addOnFailureListener(failureListener);
    }
}
