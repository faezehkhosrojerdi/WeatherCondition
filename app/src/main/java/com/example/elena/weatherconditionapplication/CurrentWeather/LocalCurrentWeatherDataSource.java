package com.example.elena.weatherconditionapplication.CurrentWeather;

import android.content.Context;

/**
 * Created by Elena on 3/10/2018.
 */

public class LocalCurrentWeatherDataSource implements CurrentWeatherDataSource {
    private Context context;
    private  CurrentWeatherSharedPrefManager currentWeatherSharedPrefManager;
    private com.example.elena.weatherconditionapplication.CurrentWeather.weather weather;

    public LocalCurrentWeatherDataSource(Context context){
        this.context=context;
        currentWeatherSharedPrefManager=new CurrentWeatherSharedPrefManager(context);
    }
    @Override
    public void getCurrentWeather(String name, GetCallBack getCallBack) {
        //get currentwether from sharedprefarence

        weather = new weather();
        try {
            weather.setName(currentWeatherSharedPrefManager.getName());
            weather.setDescription(currentWeatherSharedPrefManager.getDescription());
            weather.setMain(currentWeatherSharedPrefManager.getMain());
            weather.setTemp(currentWeatherSharedPrefManager.getTemp());
            weather.setPressure(currentWeatherSharedPrefManager.getPressure());
            weather.setHumidity(currentWeatherSharedPrefManager.getHumidity());
            weather.setTemp_max(currentWeatherSharedPrefManager.getTemp_max());
            weather.setTemp_min(currentWeatherSharedPrefManager.getTemp_min());
            weather.setWindDegree(currentWeatherSharedPrefManager.getWindDegree());
            weather.setWindSpeed(currentWeatherSharedPrefManager.getWindSpeed());
            getCallBack.OnLoad(weather);
         //   Toast.makeText(context,"Your Internet is DISCONNECT",Toast.LENGTH_LONG).show();
        } catch ( Exception e){
            getCallBack.OnError(e.toString());

        }
    }


}
