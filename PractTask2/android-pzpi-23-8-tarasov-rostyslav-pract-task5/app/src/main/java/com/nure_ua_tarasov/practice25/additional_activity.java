package com.nure_ua_tarasov.practice25;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class additional_activity extends AppCompatActivity {

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

        findViewById(R.id.previous_activity_button).setOnClickListener(this::previos_activity);
    }

    private void previos_activity(View view) {
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
}