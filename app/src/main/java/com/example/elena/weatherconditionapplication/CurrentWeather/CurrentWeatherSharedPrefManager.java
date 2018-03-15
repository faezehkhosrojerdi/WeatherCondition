package com.example.elena.weatherconditionapplication.CurrentWeather;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Elena on 3/10/2018.
 */

public class CurrentWeatherSharedPrefManager {
    private static final String EXTRA_KEY_ID = "Id";
    private static final String EXTRA_KEY_MAIN = "main";
    private static final String EXTRA_KEY_DESCRIPTION = "description";
    private static final String EXTRA_KEY_NAME = "name";
    private static final String EXTRA_KEY_LAT = "Lat";
    private static final String EXTRA_KEY_Lon = "Lon";
    private static final String EXTRA_KEY_TEMP = "temp";
    private static final String EXTRA_KEY_PRESSURE = "pressure";
    private static final String EXTRA_KEY_HUMIDITY = "humidity";
    private static final String EXTRA_KEY_TEMP_MIN = "temp_min";
    private static final String EXTRA_KEY_TEMP_MAX = "temp_max";
    private static final String EXTRA_KEY_WIND_SPEED = "windSpeed";
    private static final String EXTRA_KEY_WIND_DEGREE = "windDegree";

    private SharedPreferences sharedPreferences;

    public CurrentWeatherSharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences("currentwheathershpref", Context.MODE_PRIVATE);
    }

    public void SaveCurrentWeather(weather weather) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(EXTRA_KEY_ID, weather.getId());
        editor.putString(EXTRA_KEY_MAIN, weather.getMain());
        editor.putString(EXTRA_KEY_DESCRIPTION, weather.getDescription());
        editor.putString(EXTRA_KEY_NAME, weather.getName());
        editor.putFloat(EXTRA_KEY_LAT, (float) weather.getLat());
        editor.putFloat(EXTRA_KEY_Lon, (float) weather.getLon());
        editor.putFloat(EXTRA_KEY_TEMP, (float) weather.getTemp());
        editor.putFloat(EXTRA_KEY_PRESSURE, (float) weather.getPressure());
        editor.putFloat(EXTRA_KEY_HUMIDITY, (float) weather.getHumidity());
        editor.putFloat(EXTRA_KEY_TEMP_MIN, (float) weather.getTemp_min());
        editor.putFloat(EXTRA_KEY_TEMP_MAX, (float) weather.getTemp_max());
        editor.putFloat(EXTRA_KEY_WIND_SPEED,weather.getWindSpeed());
        editor.putFloat(EXTRA_KEY_WIND_DEGREE,weather.getWindDegree());
        editor.commit();

    }

    public  String getMain(){
        return sharedPreferences.getString(EXTRA_KEY_MAIN,"");

    }

    public String getDescription(){
        return sharedPreferences.getString(EXTRA_KEY_DESCRIPTION,"");

    }

    public  String getName(){
        return sharedPreferences.getString(EXTRA_KEY_NAME,"");
    }

    public  float getLat(){
        return sharedPreferences.getFloat(EXTRA_KEY_LAT,-1);

    }

    public  float getLon(){
        return sharedPreferences.getFloat(EXTRA_KEY_Lon,-1);

    }

    public  float getTemp(){
        return sharedPreferences.getFloat(EXTRA_KEY_TEMP,200000);
    }

    public  float getPressure(){
        return sharedPreferences.getFloat(EXTRA_KEY_PRESSURE,200000);
    }

    public float getHumidity(){
        return  sharedPreferences.getFloat(EXTRA_KEY_HUMIDITY,200000);
    }

    public  float getTemp_min(){
        return sharedPreferences.getFloat(EXTRA_KEY_TEMP_MIN,200000);
    }

    public  float getTemp_max(){
        return  sharedPreferences.getFloat(EXTRA_KEY_TEMP_MAX,200000);
    }

    public  float getWindSpeed(){
        return sharedPreferences.getFloat(EXTRA_KEY_WIND_SPEED,200000);
    }
    public  float getWindDegree(){return  sharedPreferences.getFloat(EXTRA_KEY_WIND_DEGREE,2000000);
    }

    public  weather getweather(){
        weather weather=new weather();
        weather.setId(weather.getId());
        weather.setDescription(sharedPreferences.getString(EXTRA_KEY_DESCRIPTION,"") );
        weather.setMain(sharedPreferences.getString(EXTRA_KEY_MAIN,""));
        weather.setName( sharedPreferences.getString(EXTRA_KEY_NAME,""));
        weather.setLat(sharedPreferences.getFloat(EXTRA_KEY_LAT,-1));
        weather.setLon(sharedPreferences.getFloat(EXTRA_KEY_Lon,-1));
        weather.setTemp(sharedPreferences.getFloat(EXTRA_KEY_TEMP,200000));
        weather.setPressure(sharedPreferences.getFloat(EXTRA_KEY_PRESSURE,200000));
        weather.setHumidity( sharedPreferences.getFloat(EXTRA_KEY_HUMIDITY,200000));
        weather.setTemp_min(sharedPreferences.getFloat(EXTRA_KEY_TEMP_MIN,200000));
        weather.setTemp_max(sharedPreferences.getFloat(EXTRA_KEY_TEMP_MAX,200000));
        weather.setWindSpeed(sharedPreferences.getFloat(EXTRA_KEY_WIND_SPEED,200000));
        weather.setWindDegree(sharedPreferences.getFloat(EXTRA_KEY_WIND_DEGREE,2000000));


        return weather;
    }

}

