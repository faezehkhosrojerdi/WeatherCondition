package com.example.elena.weatherconditionapplication.ForecastWeather;


import java.util.List;

/**
 * Created by Elena on 3/11/2018.
 */

public interface CurrentForecastDataSource {


        void getCurrentForecast(String name, GetCallBack getCallBack);

        interface GetCallBack{
            void OnLoad(List<forecast> forecastList);
            void OnError(String message);


        }


}
