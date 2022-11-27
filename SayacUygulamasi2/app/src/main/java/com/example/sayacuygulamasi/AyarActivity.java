package com.example.sayacuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class AyarActivity extends AppCompatActivity {
Button up_plus,up_minus,low_minus,low_plus;
CheckBox checkBoxup_vib, checkBoxup_sound,checkBoxlow_vib,checkBoxlow_sound;
EditText upperLimit,lowerLimit;
AyarlarClass ayarlarClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayar);
        up_plus = (Button) findViewById(R.id.up_plus);
        up_minus = (Button) findViewById(R.id.up_minus);
        low_plus = (Button) findViewById(R.id.low_plus);
        low_minus = (Button) findViewById(R.id.low_minus);
        checkBoxup_vib = (CheckBox) findViewById(R.id.checkBoxup_vib);
        checkBoxup_sound = (CheckBox) findViewById(R.id.checkBoxup_sound);
        checkBoxlow_sound = (CheckBox) findViewById(R.id.checkBoxlow_sound);
        checkBoxlow_vib = (CheckBox) findViewById(R.id.checkBoxlow_vib);
        upperLimit = (EditText) findViewById(R.id.upperLimit);
        lowerLimit = (EditText) findViewById(R.id.lowerLimit);
        ayarlarClass = AyarlarClass.getAyarlarClass(getApplicationContext());


        up_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            ayarlarClass.upperLimit++;
                upperLimit.setText(String.valueOf(ayarlarClass.upperLimit));
            }
        });
        up_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ayarlarClass.upperLimit--;
                upperLimit.setText(String.valueOf(ayarlarClass.upperLimit));

            }
        });
        low_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ayarlarClass.lowerLimit--;
                lowerLimit.setText(String.valueOf(ayarlarClass.lowerLimit));
            }
        });


        low_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ayarlarClass.lowerLimit++;
                lowerLimit.setText(String.valueOf(ayarlarClass.lowerLimit));
            }
        });


    }



    @Override
    protected void onPause() {
        super.onPause();
        ayarlarClass.saveValues();
    }

    @Override
    protected void onResume() {
        super.onResume();
        lowerLimit.setText(String.valueOf(ayarlarClass.lowerLimit));
        upperLimit.setText(String.valueOf(ayarlarClass.upperLimit));

        checkBoxup_vib.setChecked(ayarlarClass.upperVib);
        checkBoxup_sound.setChecked(ayarlarClass.upperSound);
        checkBoxlow_sound.setChecked(ayarlarClass.lowerSound);
        checkBoxlow_vib.setChecked(ayarlarClass.lowerVib);
    }
}