package com.example.elena.weatherconditionapplication.ForecastWeather;


import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.elena.weatherconditionapplication.Room.AsyncTaskUpdateDatabase;
import com.example.elena.weatherconditionapplication.Room.DBInjector;
import com.example.elena.weatherconditionapplication.Room.ForecastDAO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Elena on 3/11/2018.
 */

public class RemoteForecastDataSource implements CurrentForecastDataSource {

    private static final String EXTERA_KEY_URL_API_PART1= "http://api.openweathermap.org/data/2.5/forecast/daily?q=";
    private static final String EXTERA_KEY_URL_API_PART2="&apikey=0067ea3ffc9cad0548529afa3639f76f";
    private Context context;
    private List<forecast> forecasts;
    private ForecastDAO forecastDAO;



    public RemoteForecastDataSource(Context context) {
        this.context = context;
        this.forecasts = new ArrayList<>();
        this.forecastDAO=DBInjector.provideContactDao(context);


    }

    @Override
    public void getCurrentForecast(String name, final GetCallBack getCallBack) {
        // volley request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, EXTERA_KEY_URL_API_PART1+name+EXTERA_KEY_URL_API_PART2 ,null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "onResponse: " + response.toString());
                List<forecast> forecastList=new ArrayList<>();
                forecastList=parseResponseToForecasts(response);
                AsyncTaskUpdateDatabase asyncTaskUpdateDatabase=new AsyncTaskUpdateDatabase(forecastList);
                asyncTaskUpdateDatabase.execute(forecastDAO);
               // asyncTaskUpdateDatabase.cancel(true);
                getCallBack.OnLoad(forecastList);




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: " + error.toString());
                getCallBack.OnError(error.toString());
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(8000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);


    }

    private List<forecast> parseResponseToForecasts(JSONObject response) {


        try {
            JSONArray forecastJsonArray = response.getJSONArray("list");
            for (int i = 0; i < forecastJsonArray.length(); i++) {

                forecast forecast = new forecast();
                forecast.setId(i);
                JSONObject forecastJsonObject = forecastJsonArray.getJSONObject(i);
                forecast.setDt(forecastJsonObject.getLong("dt"));
                JSONObject forecatJsonObjecttemp = forecastJsonObject.getJSONObject("temp");
                forecast.setMintemp(forecatJsonObjecttemp.getDouble("min"));
                forecast.setMaxtemp(forecatJsonObjecttemp.getDouble("max"));
                JSONArray forecatJsonArrayWeathet = forecastJsonObject.getJSONArray("weather");
                JSONObject forecastJsonObjectWheather = forecatJsonArrayWeathet.getJSONObject(0);
                forecast.setMain(forecastJsonObjectWheather.getString("main"));

                forecasts.add(forecast);


            }

        } catch (JSONException e) {
            e.printStackTrace();
            forecasts = null;
        }
        return forecasts;
    }


}
