package com.nure_ua_tarasov.android_pzpi_23_8_tarasov_rostyslav_lab_task3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private String currentOperator;
    private double firstOperand;
    private double secondOperand;
    private boolean isNewOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editText = findViewById(R.id.editText);
        currentOperator = "";
        isNewOperation = true;

        setNumberButtonListeners();
        setOperatorButtonListeners();
    }
    private void setNumberButtonListeners() {
        int[] numberButtonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9
        };

        View.OnClickListener numberButtonListener = v -> {
            Button button = (Button) v;
            String buttonText = button.getText().toString();

            if (isNewOperation) {
                editText.setText(buttonText);
                isNewOperation = false;
            } else {
                editText.append(buttonText);
            }
        };

        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(numberButtonListener);
        }
    }
    private void setOperatorButtonListeners() {
        int[] operatorButtonIds = {
                R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply, R.id.buttonDivide, R.id.buttonEquals, R.id.buttonClear
        };

        View.OnClickListener operatorButtonListener = v -> {
            Button button = (Button) v;
            String buttonText = button.getText().toString();

            switch (buttonText) {
                case "+":
                case "-":
                case "*":
                case "/":
                    currentOperator = buttonText;
                    firstOperand = Double.parseDouble(editText.getText().toString());
                    isNewOperation = true;
                    break;
                case "=":
                    secondOperand = Double.parseDouble(editText.getText().toString());
                    double result = performOperation(firstOperand, secondOperand, currentOperator);
                    editText.setText(String.valueOf(result));
                    isNewOperation = true;
                    break;
                case "C":
                    editText.setText("");
                    currentOperator = "";
                    firstOperand = 0;
                    secondOperand = 0;
                    isNewOperation = true;
                    break;
            }
        };

        for (int id : operatorButtonIds) {
            findViewById(id).setOnClickListener(operatorButtonListener);
        }
    }

    private double performOperation(double firstOperand, double secondOperand, String operator) {
        switch (operator) {
            case "+":
                return firstOperand + secondOperand;
            case "-":
                return firstOperand - secondOperand;
            case "*":
                return firstOperand * secondOperand;
            case "/":
                if (secondOperand != 0) {
                    return firstOperand / secondOperand;
                } else {
                    return 0;
                }
            default:
                return 0;
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }
}