package com.example.elena.weatherconditionapplication.Room;

import android.content.Context;

/**
 * Created by Elena on 3/12/2018.
 */

public class DBInjector {
    public static ForecastDAO provideContactDao(Context context) {
        return RoomDatabase.getInstance(context).forecastDAO();
    }
}
