package com.nure_ua_tarasov.practtask3;

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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText editTextData;
    private TextView textViewFileContent;

    private static final String FILE_NAME = "user_data.txt";

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

        editTextData = findViewById(R.id.editTextData);
        Button buttonSaveToFile = findViewById(R.id.buttonSaveToFile);
        Button buttonLoadFromFile = findViewById(R.id.buttonLoadFromFile);
        textViewFileContent = findViewById(R.id.textViewFileContent);

        buttonSaveToFile.setOnClickListener(v -> saveToFile());

        buttonLoadFromFile.setOnClickListener(v -> loadFromFile());
    }

    private void saveToFile() {
        String data = editTextData.getText().toString();
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(data.getBytes());
            editTextData.setText("");
            textViewFileContent.setText("Data saved to file");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void loadFromFile() {
        FileInputStream fis = null;

        try {
            fis = openFileInput(FILE_NAME);
            int size;
            StringBuilder sb = new StringBuilder();
            while ((size = fis.read()) != -1) {
                sb.append((char) size);
            }
            textViewFileContent.setText(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}