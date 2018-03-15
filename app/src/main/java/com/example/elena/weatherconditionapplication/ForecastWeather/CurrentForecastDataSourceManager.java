package com.example.elena.weatherconditionapplication.ForecastWeather;


import android.content.Context;

import com.example.elena.weatherconditionapplication.Utils.CheakNetwork;

/**
 * Created by Elena on 3/11/2018.
 */

public class CurrentForecastDataSourceManager implements CurrentForecastDataSource{
    private RemoteForecastDataSource remoteForecastDataSource;
    private LocalForacastDataSource localForacastDataSource;
    private Context context;



    public  CurrentForecastDataSourceManager(RemoteForecastDataSource remoteForecastDataSource,
                                                 LocalForacastDataSource localForacastDataSource,
                                                 Context context) {

        this.remoteForecastDataSource=remoteForecastDataSource;
        this.localForacastDataSource=localForacastDataSource;
        this.context=context;


    }


    @Override
    public void getCurrentForecast(String name, GetCallBack getCallBack) {

        if(CheakNetwork.checkConnection(context)){
       // if(100>20) {//internet darim
            remoteForecastDataSource.getCurrentForecast(name, getCallBack);
        }
            else
            localForacastDataSource.getCurrentForecast(name,getCallBack);

    }
}
