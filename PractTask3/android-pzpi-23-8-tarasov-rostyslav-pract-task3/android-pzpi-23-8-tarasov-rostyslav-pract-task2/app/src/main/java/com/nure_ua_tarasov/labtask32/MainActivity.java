package com.nure_ua_tarasov.labtask32;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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
        Handler handler = new Handler(Looper.getMainLooper());

        Button startHandlerButton = findViewById(R.id.startHandlerButton);
        startHandlerButton.setOnClickListener(v -> handler.postDelayed(() -> {
            TextView textView = findViewById(R.id.handlerMessageTextView);
            textView.setText("Handler executed after delay");
        }, 2000));
        // Створюємо Handler для обробки повідомлень
        Handler handler3 = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                // Отримуємо повідомлення та оновлюємо інтерфейс
                TextView textView = findViewById(R.id.handlerMessageTextView);
                textView.setText("Message received: " + msg.what);
            }
        };

        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message msg = handler.obtainMessage();
            msg.what = 1;
            handler3.sendMessage(msg);
        }).start();

        Handler handler2 = new Handler(Looper.getMainLooper());

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            handler2.post(() -> {
                TextView textView = findViewById(R.id.handlerMessageTextView);
                textView.setText("Updated from background thread");
            });
        }).start();

    }
}