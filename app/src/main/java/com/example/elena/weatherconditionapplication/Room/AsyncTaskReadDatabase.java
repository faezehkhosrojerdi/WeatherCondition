package com.example.elena.weatherconditionapplication.Room;

import android.os.AsyncTask;

import com.example.elena.weatherconditionapplication.ForecastWeather.forecast;

import java.util.List;

/**
 * Created by Elena on 3/12/2018.
 */

public class AsyncTaskReadDatabase extends AsyncTask<ForecastDAO,Void,List<forecast>> {

    private List<forecast> forecastList;
    public List<forecast> forecastList1;
    public AsyncTaskReadDatabase(List<forecast> forecastList){
        this.forecastList=forecastList;}



    @Override
    protected List<forecast> doInBackground(ForecastDAO... forecastDAOS) {
      //  forecastList=forecastDAOS[0].gellAllforecast();
        return forecastList;
    }


    @Override
    protected void onPostExecute(List<forecast> forecastList) {
        super.onPostExecute(forecastList);
       forecastList1=forecastList;
    }


}
