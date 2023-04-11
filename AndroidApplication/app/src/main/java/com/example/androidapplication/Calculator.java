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
    private TextView calculate1;
    private int val1 = 0;
    private StringBuilder strSum = new StringBuilder();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        calcResult = findViewById(R.id.calcResult);
        calculate1 = findViewById(R.id.calculate1);
    }

    @SuppressLint("NonConstantResourceId")
    public void calculatorBtnPressed(View view) {
        Button button = findViewById(view.getId());
        String buttonText = button.getText().toString();
        switch (buttonText) {
            case "+":
                onAddButton();
                break;
            case "-":
                onMinusButton();
                break;
            case "*":
                onMultiplicationButton();
                break;
            case "÷":
                onDivisionButton();
                break;
            case "%":
                // onProcentButton();
                Toast.makeText(this, "% not implemented yet", Toast.LENGTH_SHORT).show();
                break;
            case ",":
                // onCommaButton();
                Toast.makeText(this, ", not implemented yet", Toast.LENGTH_SHORT).show();
                break;
            case "C":
                onClearButton();
                break;
            case "=":
                onEqualButton();
                break;
            default:
                System.out.println(buttonText);
                handleDigitInput(buttonText);
                calculate1.append(buttonText);
                break;
        }
    }

    private void handleDigitInput(String digit) {
        if (strSum.toString().equals("0")) {
            strSum.setLength(0);
        }
        if (strSum.length() < 9) {
            strSum.append(digit);
        }
    }

    public boolean validate() {
        if (strSum.length() == 0) {
            Toast.makeText(this, "Please enter a number first", Toast.LENGTH_SHORT).show();
            return false;
        } else if (val1 != 0) {
            performCalculation();
        } else {
            val1 = Integer.parseInt(strSum.toString());
        }
        strSum.setLength(0);
        return true;
    }

    public void onAddButton() {
        if (validate()) {
            calculate1.append("+");
        }
    }
    public void onMinusButton() {
        if (validate()) {
            calculate1.append("-");
        }
    }
    public void onMultiplicationButton() {
        if (validate()) {
            calculate1.append("*");
        }
    }
    public void onDivisionButton() {
        if (validate()) {
            calculate1.append("÷");
        }
    }

    public void onClearButton() {
        val1 = 0;
        strSum.setLength(0);
        calcResult.setText("0");
        calculate1.setText("");
    }

    public void onEqualButton() {
        if (strSum.length() == 0) {
            Toast.makeText(this, "Please enter a number first", Toast.LENGTH_SHORT).show();
            return;
        }
        performCalculation();
        calcResult.setText(String.valueOf(val1));
        strSum.setLength(0);
        val1 = 0;
        calculate1.setText("");
    }

    private void performCalculation() {
        int val2 = Integer.parseInt(strSum.toString());
        if (calculate1.getText().toString().contains("+")) {
            val1 = val1 + val2;
        } else if (calculate1.getText().toString().contains("-")) {
            val1 = val1 - val2;
        } else if (calculate1.getText().toString().contains("*")) {
            val1 = val1 * val2;
        } else if (calculate1.getText().toString().contains("÷")) {
            val1 = val1 / val2;
        }
    }


    /* Navigation */
    public void homePressed(View view) {
        finish();
    }
}