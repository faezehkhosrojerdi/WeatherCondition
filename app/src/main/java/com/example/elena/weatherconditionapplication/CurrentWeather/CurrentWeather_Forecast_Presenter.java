package com.example.elena.weatherconditionapplication.CurrentWeather;

import com.example.elena.weatherconditionapplication.ForecastWeather.CurrentForecastDataSource;
import com.example.elena.weatherconditionapplication.ForecastWeather.forecast;
import com.example.elena.weatherconditionapplication.ForecastWeather.forecastAdapter;
import com.example.elena.weatherconditionapplication.MainActivity;

import java.util.List;

/**
 * Created by Elena on 3/10/2018.
 */

public class CurrentWeather_Forecast_Presenter implements WeatherContract.presenter,CurrentWeatherDataSource.GetCallBack ,CurrentForecastDataSource.GetCallBack{

    private WeatherContract.view view;
    private  CurrentWeatherDataSource currentWeatherDataSource;
    private CurrentForecastDataSource currentForecastDataSource;



    public CurrentWeather_Forecast_Presenter(CurrentWeatherDataSource currentWeatherDataSource, CurrentForecastDataSource currentForecastDataSource){
        this.currentWeatherDataSource=currentWeatherDataSource;
        this.currentForecastDataSource=currentForecastDataSource;

    }


    @Override
    public void attachView(Object view) {
        this.view= (WeatherContract.view) view;
        loadCurrentWeather();
        loadCurrentForecast();
    }

    @Override
    public void detach() {

        this.view=null;
    }

    @Override
    public void loadCurrentWeather() {

        view.setProgressBarIndicator(true);
        currentWeatherDataSource.getCurrentWeather(MainActivity.city_name_for_request,this);
    }

    @Override
    public void loadCurrentForecast() {

        view.setProgressBarIndicator(true);
        currentForecastDataSource.getCurrentForecast(MainActivity.city_name_for_request,this);
    }


    @Override
    public void OnLoad(weather weather) {
        if(view!=null){
           // view.setProgressBarIndicator(false);
            view.showWeather(weather);


        }
    }

    @Override
    public void OnLoad(List<forecast> forecastList) {
        if(view!=null) {
            // view.setProgressBarIndicator(false);
            view.showForecastList(forecastList);


        }

        }

    @Override
    public void OnError(String message) {
        if(view!=null){
            view.showErrorMessage(message);
        }

    }


    public void onRefreshClick( String location, forecastAdapter forecastAdapter){
        RefreshCurrentWeather(location);
        ClaerAdapter(forecastAdapter);
        RefreshForecast(location);

    }

    public void ondetailsClick(){

    }
    public void onLocationClick(){

    }

    public  void RefreshCurrentWeather(String location){
        view.setProgressBarIndicator(true);
        currentWeatherDataSource.getCurrentWeather(location,this);

    }

    private void RefreshForecast(String location) {

        currentForecastDataSource.getCurrentForecast(location,this);
    }

    public  void ClaerAdapter(forecastAdapter forecastAdapter){
        forecastAdapter.RemoveAll();

    }
}
