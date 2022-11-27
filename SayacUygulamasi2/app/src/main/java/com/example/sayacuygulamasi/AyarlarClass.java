package com.example.sayacuygulamasi;

import android.content.Context;
import android.content.SharedPreferences;

public class AyarlarClass {
    int upperLimit,lowerLimit,currentValue;
    boolean upperVib,upperSound,lowerVib,lowerSound;
    static AyarlarClass ayarlarClass= null;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;
    private AyarlarClass(Context context){
        sharedPreferences= context.getSharedPreferences("ayarlar",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        loadValues();
    }
    public static AyarlarClass getAyarlarClass(Context context){
        if(ayarlarClass==null){
            ayarlarClass= new AyarlarClass(context);
        }
        return ayarlarClass;
    }
    public void loadValues(){
        upperLimit = sharedPreferences.getInt("upperLimit",20);
        lowerLimit= sharedPreferences.getInt("lowerLimit",0);
        currentValue= sharedPreferences.getInt("currentValue",0);
        upperVib= sharedPreferences.getBoolean("upperVib",false);
        lowerVib= sharedPreferences.getBoolean("lowerVib",false);
        upperSound= sharedPreferences.getBoolean("upperSound",false);
        lowerSound= sharedPreferences.getBoolean("lowerSound",false);
    }

    public void saveValues(){
        editor.putInt("upperLimit",upperLimit);
        editor.putInt("lowerLimit",lowerLimit);
        editor.putInt("currentValue",currentValue);
        editor.putBoolean("upperVib",upperVib);
        editor.putBoolean("upperSound",upperSound);
        editor.putBoolean("lowerVib",lowerVib);
        editor.putBoolean("lowerSound",lowerSound);
        editor.commit();
    }



}
