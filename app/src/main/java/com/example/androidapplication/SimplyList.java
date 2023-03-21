package com.example.androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidapplication.adapter.MyAdapter;
import com.example.androidapplication.model.Item;

import java.util.ArrayList;
import java.util.List;

public class SimplyList extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private MyAdapter myAdapter;

    //private String[] values = new String[3];
    private List<Item> values = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simply_list);

        addValues();
        listView = findViewById(R.id.listView);
        // adapter = new ArrayAdapter<>(this, R.layout.myrow, R.id.rowTextView, values);

        // Make custom adapter (extends BaseAdapter)
        //
        myAdapter = new MyAdapter(values, this);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener((listView, linearLayout, pos, id) -> {
            // System.out.println("parent: " + listView.getClass().getName()); // parent
            // System.out.println("view: " + linearLayout.getClass().getName()); // view
            TextView tv = linearLayout.findViewById(R.id.rowTextView);
            System.out.println("you pressed " + tv.getText());
            Intent intent = new Intent(this, DetailActivity.class);
            startActivity(intent);
        });
    }

    private void addValues() {
        values.add(new Item("Car 1", R.drawable.car1));
        values.add(new Item("Car 2", R.drawable.car2));
    }

    public void addPressed(View view) {
        values.add(new Item("New item", R.drawable.car0));
        myAdapter.notifyDataSetChanged(); // Should update the layout
    }
}