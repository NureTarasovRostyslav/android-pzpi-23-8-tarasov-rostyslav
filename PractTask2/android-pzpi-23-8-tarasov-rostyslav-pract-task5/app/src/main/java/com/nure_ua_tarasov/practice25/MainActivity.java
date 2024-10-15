package com.nure_ua_tarasov.practice25;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private final int[] ids_to_save = {
            R.id.edit_text_1
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("onCreate", "onCreate execution");
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.next_act_button).setOnClickListener(this::nextActivity);
    }

    private void nextActivity(View view) {
        Intent next_activity = new Intent(this, additional_activity.class);
        startActivity(next_activity);
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
            if (view instanceof EditText) {
                outState.putString(String.valueOf(id), ((EditText) view).getText().toString());
            }
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        for (int id : ids_to_save) {
            View view = findViewById(id);
            if (view instanceof EditText) {
                ((EditText) view).setText(savedInstanceState.getString(String.valueOf(id)));
            }
        }
    }
}