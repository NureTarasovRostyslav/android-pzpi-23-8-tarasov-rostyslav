package com.nure_ua_tarasov.labtask31;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
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
        setOnClickListeners();

    }

    private void setOnClickListeners() {
        Button showDialogButton = findViewById(R.id.showDialogButton);
        Button showDatePickerButton = findViewById(R.id.showDatePickerButton);
        Button showCustomDialogButton = findViewById(R.id.showCustomDialogButton);

        showCustomDialogButton.setOnClickListener(v -> {
            LayoutInflater inflater = getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.custom_dialog, null);

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setView(dialogView)
                    .setPositiveButton("OK", (dialog, id) -> {
                        // Обробка даних
                    })
                    .setNegativeButton("Cancel", (dialog, id) -> {
                        // Закриття діалогу
                    });
            builder.create().show();
        });
        showDatePickerButton.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                    (view, year, month, dayOfMonth) -> {

                    }, 2023, 8, 1);
            datePickerDialog.show();
        });
        showDialogButton.setOnClickListener(v -> new AlertDialog.Builder(MainActivity.this)
                .setTitle("Діалог")
                .setMessage("Це приклад AlertDialog.")
                .setPositiveButton("OK", (dialog, which) -> {
                    // Дія при натисканні OK
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    // Дія при натисканні Cancel
                })
                .show());
    }


}