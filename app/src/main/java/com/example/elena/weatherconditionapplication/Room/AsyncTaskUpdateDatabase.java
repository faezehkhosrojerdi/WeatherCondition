package com.example.elena.weatherconditionapplication.Room;

import android.os.AsyncTask;

import com.example.elena.weatherconditionapplication.ForecastWeather.forecast;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Elena on 3/12/2018.
 */

public class AsyncTaskUpdateDatabase extends AsyncTask<ForecastDAO,Void,Void> {

private List<forecast> forecastList;
public  AsyncTaskUpdateDatabase(List<forecast> forecastList){
    this.forecastList=forecastList;
}

    @Override
    protected Void doInBackground(ForecastDAO... forecastDAOS) {
        forecastDAOS[0].deleteAll();
        for (forecast item :forecastList) {

          // forecastDAOS[0].insertToDB(item);

        }

        return null;
    }



    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}
