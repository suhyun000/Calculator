package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    boolean isFirstInput = true;
    double resultNumber = 0;
    double inputNumber = 0;
    String operator = "=";
    String lastOperater = "+";
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
    }

    public void numButtonClick(View view){
        String getButtonText = view.getTag().toString();
        if(isFirstInput) {
            activityMainBinding.resultTextView.setText(view.getTag().toString());
            isFirstInput = false;
        }else {
            if(activityMainBinding.resultTextView.getText().toString().equals("0")){
                Toast.makeText(this, "0으로 시작되는 숫자는 없습니다.", Toast.LENGTH_SHORT).show();
                isFirstInput = true;
            } else{
                activityMainBinding.resultTextView.append(view.getTag().toString());
            }
        }
    }

    public void allClearButtonClick (View view){
        activityMainBinding.resultTextView.setText("0");
        activityMainBinding.mathTextView.setText("");
        resultNumber = 0;
        operator = "=";
        isFirstInput = true;
    }

    public void pointButtonClick (View view){
        if(isFirstInput) {
            activityMainBinding.resultTextView.setText("0" + view.getTag().toString());
        }else{
            if(activityMainBinding.resultTextView.getText().toString().contains(".")){
                Toast.makeText(this, "이미 소숫점이 존재합니다.", Toast.LENGTH_SHORT).show();
            }else {
                activityMainBinding.resultTextView.append(view.getTag().toString());
            }
        }
    }

    public void operatorClick (View view){
            inputNumber = Double.parseDouble(activityMainBinding.resultTextView.getText().toString());

        resultNumber = calculator(resultNumber, inputNumber, operator);

        activityMainBinding.resultTextView.setText(String.valueOf(resultNumber));
            isFirstInput = true;
            operator = view.getTag().toString();
            activityMainBinding.mathTextView.append(inputNumber + " " + operator + " ");

    }


    public void equalsButtonClick (View view){
        if(isFirstInput){
            activityMainBinding.mathTextView.setText(resultNumber + " " + lastOperater + " " + inputNumber + " ");
            resultNumber = calculator(resultNumber, inputNumber, lastOperater);
            activityMainBinding.resultTextView.setText(String.valueOf(resultNumber));
        }else {
            inputNumber = Double.parseDouble(activityMainBinding.resultTextView.getText().toString());

            resultNumber = calculator(resultNumber, inputNumber, operator);
            lastOperater = operator;
            activityMainBinding.resultTextView.setText(String.valueOf(resultNumber));
            isFirstInput = true;
            operator = view.getTag().toString();
            activityMainBinding.mathTextView.append(inputNumber + " " + operator + " ");
        }


    }

    private double calculator(double resultNumber, double inputNumber, String operator) {
        switch (operator){
            case "=":
                resultNumber = inputNumber;
                break;
            case "+":
                resultNumber = resultNumber + inputNumber;
                break;
            case "-":
                resultNumber = resultNumber - inputNumber;
                break;
            case "*":
                resultNumber = resultNumber * inputNumber;
                break;
            case "÷":
                resultNumber = resultNumber / inputNumber;
                break;
        }

        return resultNumber;
    }
}

