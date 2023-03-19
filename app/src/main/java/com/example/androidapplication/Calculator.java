package com.example.androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Calculator extends AppCompatActivity {

    private TextView calcResult;
    private Button btnNr1, btnNr2, btnNr3, btnNr4, btnNr5, btnNr6, btnNr7, btnNr8, btnNr9, btnNr0, btnEqual, btnAdd, btnClear;

    private int val1 = 0;
    private StringBuilder strSum = new StringBuilder();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        btnNr1 = findViewById(R.id.btnNr1);
        btnNr2 = findViewById(R.id.btnNr2);
        btnNr3 = findViewById(R.id.btnNr3);
        btnNr4 = findViewById(R.id.btnNr4);
        btnNr5 = findViewById(R.id.btnNr5);
        btnNr6 = findViewById(R.id.btnNr6);
        btnNr7 = findViewById(R.id.btnNr7);
        btnNr8 = findViewById(R.id.btnNr8);
        btnNr9 = findViewById(R.id.btnNr9);
        btnNr0 = findViewById(R.id.btnNr0);
        btnAdd = findViewById(R.id.btnAdd);
        btnEqual = findViewById(R.id.btnEqual);
        btnClear = findViewById(R.id.btnClear);
        calcResult = findViewById(R.id.calcResult);
    }

    @SuppressLint("NonConstantResourceId")
    public void calculatorBtnPressed(View view) {
        switch (view.getId()) {
            case R.id.btnNr1:
                strSum.append(btnNr1.getText().toString());
                break;
            case R.id.btnNr2:
                strSum.append("2");
                break;
            case R.id.btnNr3:
                strSum.append("3");
                break;
            case R.id.btnNr4:
                strSum.append("4");
                break;
            case R.id.btnNr5:
                strSum.append("5");
                break;
            case R.id.btnNr6:
                strSum.append("6");
                break;
            case R.id.btnNr7:
                strSum.append("7");
                break;
            case R.id.btnNr8:
                strSum.append("8");
                break;
            case R.id.btnNr9:
                strSum.append("9");
                break;
            case R.id.btnNr0:
                strSum.append("0");
                break;
            case R.id.btnAdd:
                onAddButton();
                break;
            case R.id.btnClear:
                onClearButton();
                break;
            case R.id.btnEqual:
                onEqualButton();
                break;
        }
    }

    public void onClearButton() {
        val1 = 0;
        strSum.setLength(0);
        calcResult.setText("0");
    }

    public void onAddButton() {
        System.out.println(strSum);
        if (strSum.length() == 0) {
            Toast.makeText(this, "Please enter a number first", Toast.LENGTH_SHORT).show();
            return;
        }
        val1 = Integer.parseInt(strSum.toString());
        strSum.setLength(0);
    }

    public void onEqualButton() {
        if (strSum.length() == 0) {
            Toast.makeText(this, "Please enter a number first", Toast.LENGTH_SHORT).show();
            return;
        }
        int val2 = Integer.parseInt(strSum.toString());
        int result = val1 + val2;
        calcResult.setText(String.valueOf(result));
        strSum.setLength(0);
        val1 = 0;
    }

    /* Navigation */
    public void homePressed(View view) {
        finish();
    }
}