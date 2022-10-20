package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private TextView txtResult;
    private static int operator = 0; // 1: + ; 2: -; 3: *; 4: /;
    private boolean isOperator = false;
    private boolean isHaveResult = false;
    private String result;
    private String operandA,operandB;

    private boolean isClickFunction = false;
    private Boolean isDot = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResult = findViewById(R.id.txtResult);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnClear:
                txtResult.setText("");
//                isHaveResult=false;
                break;
            case R.id.btnPercent:
                Log.i(TAG, "onClick: Percent");
                break;
            case R.id.btn0:
            case R.id.btn1:
            case R.id.btn2:
            case R.id.btn3:
            case R.id.btn4:
            case R.id.btn5:
            case R.id.btn6:
            case R.id.btn7:
            case R.id.btn8:
            case R.id.btn9:
                if (isClickFunction){
                    txtResult.setText("");
                }
                isClickFunction=false;
                TextView textView = (TextView) v;
                Log.i(TAG, "onClick: "+textView.getText().toString());
                String str = textView.getText().toString();
                txtResult.append(str);
                break;
            case R.id.btnDot:
                if (isClickFunction){
                    txtResult.setText("");
                }
                isClickFunction=false;
                if (!isDot){
                    TextView textViewDot = (TextView) v;
                    String strDot = textViewDot.getText().toString();
                    txtResult.append(strDot);
                    isDot=true;
                }
                break;
            case R.id.btnPlus:
            case R.id.btnSub:
            case R.id.btnMulti:
            case R.id.btnDiv:
                    TextView textViewOperator = (TextView) v;
                    String strOperator = textViewOperator.getText().toString();
                    onCalculate(strOperator);

                break;
            case R.id.btnEqual:
                onEqual();
                break;
        }
    }

    private void onEqual() {
        Log.i(TAG, "onEqual: "+operandA+" "+operandB);
        switch (operator){
            case 1:
                result = (Double.parseDouble(operandA) + Double.parseDouble(txtResult.getText().toString())) + "";
                break;
            case 2:
                result = (Double.parseDouble(operandA) - Double.parseDouble(txtResult.getText().toString())) + "";
                break;
            case 3:
                result = (Double.parseDouble(operandA) * Double.parseDouble(txtResult.getText().toString())) + "";
                break;
            case 4:
                result = (Double.parseDouble(operandA) / Double.parseDouble(txtResult.getText().toString())) + "";
                break;
        }
        operandA = result;
        txtResult.setText(result);

    }

    private void onCalculate(String str) {
        isClickFunction=true;
        switch (str){
            case "+":
                operator=1;
                break;
            case "-":
                operator=2;
                break;
            case "x":
                operator=3;
                break;
            case "/":
                operator=4;
                break;
        }
        if (operandA==null){
            operandA = txtResult.getText().toString();
        }else{
            operandB = txtResult.getText().toString();
        }
        Log.i(TAG, "onCalculate: "+operandA+" "+operandB);
    }


}