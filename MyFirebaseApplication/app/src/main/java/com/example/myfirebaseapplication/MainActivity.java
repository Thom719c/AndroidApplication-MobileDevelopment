package com.example.myfirebaseapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myfirebaseapplication.adapter.MyAdapter;
import com.example.myfirebaseapplication.model.Note;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseService fs;
    private ArrayAdapter adapter;
    private ListView listView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Make an adapter
        adapter = new ArrayAdapter<>(this, R.layout.myrow, R.id.rowTextView);
        fs = new FirebaseService(adapter);
        //firebaseService.add2Note("Hi from android, with feedback!");
        //firebaseService.startListener(notes);
        fs.startListener();

        System.out.println("notes");

        showAllNotes(adapter);
    }

    public void showAllNotes(ArrayAdapter<Note> adapter) {
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((listView, linearLayout, pos, id) -> {
            TextView tv = linearLayout.findViewById(R.id.rowTextView);

            System.out.println("you pressed " + tv.getText());

            Intent intent = new Intent(this, NoteActivity.class);
            intent.putExtra("text", tv.getText());
            intent.putExtra("docId", adapter.getItem((int) id).getDocumentId());
            startActivity(intent);
        });
    }



}