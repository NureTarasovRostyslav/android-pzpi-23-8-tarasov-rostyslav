package com.nure_ua_tarasov.practice22;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

    }

    @SuppressLint("SetTextI18n")
    public void btn_click(View view) {
        if (view instanceof Button) {
            TextView output = findViewById(R.id.textView0);
            String old = output.getText().toString();

            if (((Button) view).getText().equals("backspace")){
                output.setText(removeLastChar(old));
            }else{
                output.setText(old+((Button) view).getText());
            }

        }
    }

    public static String removeLastChar(String s) {
        return (s == null || s.isEmpty())
                ? null
                : (s.substring(0, s.length() - 1));
    }
}