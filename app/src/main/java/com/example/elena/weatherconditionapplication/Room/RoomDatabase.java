package com.example.elena.weatherconditionapplication.Room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.elena.weatherconditionapplication.ForecastWeather.forecast;

/**
 * Created by Elena on 3/12/2018.
 */
@Database(entities = {forecast.class}, version = 1,exportSchema = false)
 public  abstract class RoomDatabase extends android.arch.persistence.room.RoomDatabase {

    public abstract  ForecastDAO forecastDAO();
    private static RoomDatabase instance;

    public static RoomDatabase getInstance(Context context){
        if(instance== null)
            instance= Room.databaseBuilder(context,RoomDatabase.class,"my_db").build();
        return instance;
    }
}
