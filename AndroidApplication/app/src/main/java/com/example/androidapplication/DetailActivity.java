package com.example.androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        textView = findViewById(R.id.textView2);
        imageView = findViewById(R.id.imgView);

        Intent intent = getIntent();
        String carTxt = intent.getStringExtra("carText");
        String carImg = intent.getStringExtra("carImg");

        System.out.println(carTxt + " " + carImg);

        textView.setText(carTxt);
        imageView.setImageResource(Integer.parseInt(carImg));

    }
}