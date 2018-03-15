package com.example.elena.weatherconditionapplication.Room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.elena.weatherconditionapplication.ForecastWeather.forecast;

import java.util.List;

/**
 * Created by Elena on 3/12/2018.
 */
@Dao
public interface ForecastDAO {

   @Insert
   long insertToDB(forecast forecast);


    @Query("DELETE  FROM tbl_forecast")
     void deleteAll();

    @Query("SELECT COUNT(*) FROM tbl_forecast")
    int getForecastsCount();

    @Query("SELECT * FROM tbl_forecast")
    List<forecast>gellAllforecast();
}
