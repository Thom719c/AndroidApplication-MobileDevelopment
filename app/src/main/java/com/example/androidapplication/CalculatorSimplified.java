package com.example.androidapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorSimplified extends AppCompatActivity {
    private TextView calcResult;
    private TextView calculate1;
    private Button btnEqual, btnClear, btnAdd, btnProcent, btnDivision, btnMinus, btnMultiplication, btn9;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        btnAdd = findViewById(R.id.btnAdd);
        btnEqual = findViewById(R.id.btnEqual);
        btnClear = findViewById(R.id.btnClear);
        btnProcent = findViewById(R.id.btnProcent);
        btnDivision = findViewById(R.id.btnDivision);
        btnMinus = findViewById(R.id.btnMinus);
        btnMultiplication = findViewById(R.id.btnMultiplication);
        btn9 = findViewById(R.id.button9);
        calcResult = findViewById(R.id.calcResult);
        calculate1 = findViewById(R.id.calculate1);
    }

    @SuppressLint("NonConstantResourceId")
    public void calculatorBtnPressed(View view) {
        Button button = findViewById(view.getId());
        String buttonText = button.getText().toString();
        switch (buttonText) {
            case "+":
                calculate1.append("+");
                break;
            case "-":
                calculate1.append("-");
                break;
            case "*":
                calculate1.append("*");
                break;
            case "%":
                calculate1.append("%");
                break;
            case "÷":
                calculate1.append("/");
                break;
            case "C":
                onClearButton();
                break;
            case "=":
                onEqualButton();
                break;
            default:
                System.out.println(buttonText);
                calculate1.append(buttonText);
                break;
        }
    }

    public void onClearButton() {
        calcResult.setText("0");
        calculate1.setText("");
    }

    public void onEqualButton() {
        if (calculate1.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter a number first", Toast.LENGTH_SHORT).show();
            return;
        }

        int result = 0;
        char[] operations = calculate1.getText().toString().replaceAll("[0-9]", "").toCharArray();
        String[] operands = calculate1.getText().toString().split("[^0-9]+");
        result = Integer.parseInt(operands[0]);
        for (int i = 0; i < operations.length; i++) {
            if (operations[i] == '+') {
                result += Integer.parseInt(operands[i+1]);
            } else if (operations[i] == '-') {
                result -= Integer.parseInt(operands[i+1]);
            } else if (operations[i] == '*') {
                result *= Integer.parseInt(operands[i+1]);
            } else if (operations[i] == '/') {
                result /= Integer.parseInt(operands[i+1]);
            }
        }
        calcResult.setText(String.valueOf(result));
        calculate1.setText("");
    }


    /* Navigation */
    public void homePressed(View view) {
        finish();
    }
}