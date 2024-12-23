package com.nure_ua_tarasov.practice25;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class additional_activity extends AppCompatActivity {
    private int counter = 0;
    private int[] ids_to_save = {
            R.id.counter_view
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_additional);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.previous_activity_button).setOnClickListener(this::close_activity);
        findViewById(R.id.click_counter).setOnClickListener(this::add1);
    }

    private void add1(View view) {
        counter++;
        TextView counter_view = findViewById(R.id.counter_view);
        counter_view.setText(String.valueOf(counter));

    }

    private void close_activity(View view) {
        finish();
    }
    @Override
    protected void onStart() {
        Log.d("onStart", "onStart execution " + this.getLocalClassName());
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("onResume", "onResume execution " + this.getLocalClassName());
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("onPause", "onPause execution " + this.getLocalClassName());
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("onStop", "onStop execution " + this.getLocalClassName());
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("onDestroy", "onDestroy execution " + this.getLocalClassName());
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Log.d("onRestart", "onRestart execution " + this.getLocalClassName());
        super.onRestart();
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        for (int id : ids_to_save) {
            View view = findViewById(id);
            if (view instanceof TextView) {
                outState.putString(String.valueOf(id), ((TextView) view).getText().toString());
            }
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        for (int id : ids_to_save) {
            View view = findViewById(id);
            if (view instanceof TextView) {
                ((TextView) view).setText(savedInstanceState.getString(String.valueOf(id)));
            }
        }

        counter = Integer.parseInt(Objects.requireNonNull(savedInstanceState.getString(String.valueOf(R.id.counter_view))));
    }
}