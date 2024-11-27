package com.nure_ua_tarasov.labtask1;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Life cycle", "onCreate");
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Life cycle", "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Life cycle", "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Life cycle", "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Life cycle", "onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Life cycle", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Life cycle", "onDestroy");
    }

}