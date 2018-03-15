package com.example.elena.weatherconditionapplication.CurrentWeather;

/**
 * Created by Elena on 3/10/2018.
 */

public interface CurrentWeatherDataSource {

    void getCurrentWeather(String name, GetCallBack getCallBack);

    interface GetCallBack{
        void OnLoad(weather weather);
        void OnError(String message);

    }
}
