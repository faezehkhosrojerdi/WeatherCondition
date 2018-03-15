package com.example.elena.weatherconditionapplication.ForecastWeather;


import android.content.Context;

import com.example.elena.weatherconditionapplication.Room.AsyncTaskReadDatabase;
import com.example.elena.weatherconditionapplication.Room.DBInjector;
import com.example.elena.weatherconditionapplication.Room.ForecastDAO;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Elena on 3/11/2018.
 */

public class LocalForacastDataSource implements CurrentForecastDataSource {

    private Context context;
    private List<forecast> forecastList;
    ForecastDAO forecastDAO;


    public LocalForacastDataSource(Context context) {
        this.context = context;
        this.context = context;
        this.forecastList = new ArrayList<>();
        this.forecastDAO = DBInjector.provideContactDao(context);

    }


    @Override
    public void getCurrentForecast(String name, GetCallBack getCallBack) {
        // get data from database
        try {
            AsyncTaskReadDatabase asyncTaskReadDatabase = new AsyncTaskReadDatabase(forecastList);
            asyncTaskReadDatabase.execute(forecastDAO);
            getCallBack.OnLoad(asyncTaskReadDatabase.forecastList1);

        } catch (Exception e) {
            getCallBack.OnError(e.toString());
        }


    }
}
