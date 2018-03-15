package com.example.elena.weatherconditionapplication.CurrentWeather;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;


/**
 * Created by Elena on 3/10/2018.
 */

public class RemoteCurrentWeatherDataSource implements CurrentWeatherDataSource {
    private static final String EXTERA_KEY_URL_API_PART1= "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String EXTERA_KEY_URL_API_PART2="&apikey=0067ea3ffc9cad0548529afa3639f76f";
    private   Context context;
    private CurrentWeatherSharedPrefManager currentWeatherSharedPrefManager;
    public  RemoteCurrentWeatherDataSource(Context context){
        this.context=context;
        currentWeatherSharedPrefManager=new CurrentWeatherSharedPrefManager(context);
    }
    @Override
    public void getCurrentWeather(String name, final GetCallBack getCallBack) {
        //volley request


        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET,
                EXTERA_KEY_URL_API_PART1 + name + EXTERA_KEY_URL_API_PART2,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "onResponse: "+response.toString());
                weather weather=parseResponseToWeather(response);
                currentWeatherSharedPrefManager.SaveCurrentWeather(weather);
                getCallBack.OnLoad(weather);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: "+error.toString());
                getCallBack.OnError(error.toString());
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(8000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(request);

    }

    private weather parseResponseToWeather(JSONObject response) {
        weather weather = new weather();
        try {
            JSONArray weatherJsonArray = response.getJSONArray("weather");
            JSONObject weatherJsonObject = weatherJsonArray.getJSONObject(0);
            weather.setMain(weatherJsonObject.getString("main"));
            weather.setDescription(weatherJsonObject.getString("description"));
            JSONObject mainJsonObject = response.getJSONObject("main");
            weather.setTemp((float) mainJsonObject.getDouble("temp"));
            weather.setHumidity(mainJsonObject.getInt("humidity"));
            weather.setPressure(mainJsonObject.getInt("pressure"));
            weather.setTemp_min((float) mainJsonObject.getDouble("temp_min"));
            weather.setTemp_max((float) mainJsonObject.getDouble("temp_max"));
            JSONObject windJsonObject = response.getJSONObject("wind");
            weather.setWindSpeed((float) windJsonObject.getDouble("speed"));
            weather.setWindDegree((float) windJsonObject.getDouble("deg"));
            String name = (String) response.get("name");
            weather.setName(name);
            Long dt= response.getLong("dt");
            weather.setDt(dt);

            return weather;
        } catch (JSONException e) {

            return null;

        }
    }

    public Context getContext(){
        return  context;
    }


}
