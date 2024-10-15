package com.nure_ua_tarasov.practice25;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
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

    private TextView timerTextView;
    private Handler handler = new Handler();
    private long startTime = 0L;
    private long elapsedTime = 0L;

    private Runnable updateTimerThread = new Runnable() {
        public void run() {
            long currentTime = SystemClock.uptimeMillis() - startTime;
            long totalElapsedTime = elapsedTime + currentTime;

            int secs = (int) (totalElapsedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (totalElapsedTime % 1000);

            timerTextView.setText(String.format("%02d:%02d:%03d", mins, secs, milliseconds));

            handler.postDelayed(this, 10);
        }
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
        timerTextView = findViewById(R.id.timer_view);

        if (savedInstanceState != null) {
            elapsedTime = savedInstanceState.getLong("elapsedTime");
        }
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
        startTime = SystemClock.uptimeMillis();
        handler.post(updateTimerThread);
    }

    @Override
    protected void onPause() {
        Log.d("onPause", "onPause execution " + this.getLocalClassName());
        super.onPause();
        elapsedTime += SystemClock.uptimeMillis() - startTime;
        handler.removeCallbacks(updateTimerThread);
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
        outState.putLong("elapsedTime", elapsedTime);
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