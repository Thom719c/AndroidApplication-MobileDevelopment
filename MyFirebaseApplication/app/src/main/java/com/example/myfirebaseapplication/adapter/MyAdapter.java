package com.example.myfirebaseapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfirebaseapplication.R;
import com.example.myfirebaseapplication.model.Note;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private List<Note> notes;
    private LayoutInflater inflater;

    public MyAdapter(List<Note> notes, Context context) {
        this.notes = notes;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        // inflate
        if (view == null) {
            view = inflater.inflate(R.layout.myrow, null);
        }
        TextView textView = view.findViewById(R.id.rowTextView);
        textView.setText(notes.get(position).getText());
        //ImageView imageView = view.findViewById(R.id.rowImageView);
        //imageView.setImageResource(notes.get(position).getImage());
        //imageView.setTag(notes.get(position).getImage());
        return view;
    }
}
