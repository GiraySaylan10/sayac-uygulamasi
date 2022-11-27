package com.example.sayacuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NextActivity extends AppCompatActivity {
 TextView value;
 Button minus,plus;
 int counter= 0;
 Button setup;
 AyarlarClass ayarlarClass;
    SensorManager sensorManager;
    Sensor sensorShake;
    SensorEventListener sensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        value = (TextView) findViewById(R.id.value);
        minus = (Button) findViewById(R.id.minus);
        plus = (Button) findViewById(R.id.plus);
        setup = (Button) findViewById(R.id.setup);
        Context context = getApplicationContext();
        ayarlarClass = AyarlarClass.getAyarlarClass(context);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                value.setText(String.valueOf(counter));
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter--;
                value.setText(String.valueOf(counter));
            }
        });
        setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NextActivity.this, AyarActivity.class);
                startActivity(i);
            }
        });
        value.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                counter = Integer.valueOf(value.getText().toString());
            }
        });
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorShake = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];
                float z = sensorEvent.values[2];

                float sum = Math.abs(x) + Math.abs(y) + Math.abs(z);
                if (sum > 16) {
                    ayarlarClass.currentValue = ayarlarClass.lowerLimit;
                    value.setText(String.valueOf(ayarlarClass.currentValue));
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

    }

    @Override
    protected void onResume() {
        super.onResume();
        value.setText(String.valueOf(ayarlarClass.currentValue));
        sensorManager.registerListener(sensorEventListener,sensorShake,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ayarlarClass.saveValues();
        sensorManager.unregisterListener(sensorEventListener,sensorShake);
    }
}