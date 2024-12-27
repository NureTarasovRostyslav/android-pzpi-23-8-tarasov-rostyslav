package com.nure_ua_tarasov.android_pzpi_23_8_tarasov_rostyslav_pract_task3;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextAge;
    private Button buttonSave;
    private TextView textViewResult;

    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "user_pref";
    private static final String KEY_NAME = "name";
    private static final String KEY_AGE = "age";
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

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        buttonSave = findViewById(R.id.buttonSave);
        textViewResult = findViewById(R.id.textViewResult);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        loadUserData();

        buttonSave.setOnClickListener(v -> saveUserData());
    }
    private void saveUserData() {
        String name = editTextName.getText().toString();
        String age = editTextAge.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_AGE, age);
        editor.apply();

        textViewResult.setText("Збережені дані: \nІм'я: " + name + "\nВік: " + age);
    }

    private void loadUserData() {
        String name = sharedPreferences.getString(KEY_NAME, null);
        String age = sharedPreferences.getString(KEY_AGE, null);

        if (name != null && age != null) {
            textViewResult.setText("Збережені дані: \nІм'я: " + name + "\nВік: " + age);
        }
    }

}