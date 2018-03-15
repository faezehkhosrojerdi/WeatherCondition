package com.example.elena.weatherconditionapplication.CurrentWeather;

import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.widget.Toast;

import com.example.elena.weatherconditionapplication.Utils.CheakNetwork;


/**
 * Created by Elena on 3/10/2018.
 */

public class CurrentWeatherDataSourceManager  implements CurrentWeatherDataSource  {

    private  RemoteCurrentWeatherDataSource remoteCurrentWeatherDataSource;
    private  LocalCurrentWeatherDataSource localCurrentWeatherDataSource;
    private  Context context;



    public  CurrentWeatherDataSourceManager(RemoteCurrentWeatherDataSource remoteCurrentWeatherDataSource,
                                            LocalCurrentWeatherDataSource  localCurrentWeatherDataSource,
                                            Context context) {
        this.remoteCurrentWeatherDataSource = remoteCurrentWeatherDataSource;
        this.localCurrentWeatherDataSource = localCurrentWeatherDataSource;
        this.context=context;


    }

    @Override
    public void getCurrentWeather(String name, GetCallBack getCallBack) {



        if(CheakNetwork.checkConnection(context)){
       //if(100>20){//internet darim
        remoteCurrentWeatherDataSource.getCurrentWeather(name, getCallBack);


    }
    else {
            localCurrentWeatherDataSource.getCurrentWeather(name ,getCallBack);

        }
    }


}
