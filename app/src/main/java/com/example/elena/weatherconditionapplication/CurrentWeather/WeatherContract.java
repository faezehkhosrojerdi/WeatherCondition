package com.example.elena.weatherconditionapplication.CurrentWeather;

import com.example.elena.weatherconditionapplication.common.BasePresenter;
import com.example.elena.weatherconditionapplication.common.BaseView;
import com.example.elena.weatherconditionapplication.ForecastWeather.forecast;

import java.util.List;

/**
 * Created by Elena on 3/10/2018.
 */

public interface WeatherContract extends BaseView {
    interface view {
        void setProgressBarIndicator(boolean mustshow);

        void showWeather(weather weather);
        void showForecastList(List<forecast>forecastList);
        void showErrorMessage(String message);
    }

    interface  presenter extends BasePresenter {
        void loadCurrentWeather();
        void loadCurrentForecast();


    }


}
