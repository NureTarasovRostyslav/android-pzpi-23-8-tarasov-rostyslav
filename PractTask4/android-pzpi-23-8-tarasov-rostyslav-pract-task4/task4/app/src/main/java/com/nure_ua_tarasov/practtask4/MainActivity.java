package com.nure_ua_tarasov.practtask4;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor gyroscope;
    private Sensor magnetometer;
    private SensorEventListener sensorEventListener;
    private TextView sensorData;
    private ImageView imageView;
    private Sensor currentSensor;

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
        sensorData = findViewById(R.id.sensor_data);
        imageView = findViewById(R.id.image_view);
        Spinner sensorSpinner = findViewById(R.id.sensor_spinner);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                String sensorDataText = "";

                if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                    sensorDataText = "Accelerometer Data\nX: " + x + "\nY: " + y + "\nZ: " + z;
                } else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                    sensorDataText = "Gyroscope Data\nX: " + x + "\nY: " + y + "\nZ: " + z;
                    // Рух зображення на основі даних гіроскопа
                    imageView.setRotation(imageView.getRotation() + x);
                } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                    sensorDataText = "Magnetometer Data\nX: " + x + "\nY: " + y + "\nZ: " + z;
                }

                sensorData.setText(sensorDataText);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        };

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sensors_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sensorSpinner.setAdapter(adapter);

        sensorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sensorManager.unregisterListener(sensorEventListener);
                switch (position) {
                    case 0:
                        currentSensor = accelerometer;
                        break;
                    case 1:
                        currentSensor = gyroscope;
                        break;
                    case 2:
                        currentSensor = magnetometer;
                        break;
                }
                sensorManager.registerListener(sensorEventListener, currentSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (currentSensor != null) {
            sensorManager.registerListener(sensorEventListener, currentSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }
}