package com.example.elena.weatherconditionapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elena.weatherconditionapplication.CurrentWeather.WeatherContract;
import com.example.elena.weatherconditionapplication.CurrentWeather.CurrentWeatherDataSourceManager;
import com.example.elena.weatherconditionapplication.CurrentWeather.CurrentWeather_Forecast_Presenter;
import com.example.elena.weatherconditionapplication.CurrentWeather.LocalCurrentWeatherDataSource;
import com.example.elena.weatherconditionapplication.CurrentWeather.RemoteCurrentWeatherDataSource;
import com.example.elena.weatherconditionapplication.ForecastWeather.CurrentForecastDataSourceManager;
import com.example.elena.weatherconditionapplication.ForecastWeather.LocalForacastDataSource;
import com.example.elena.weatherconditionapplication.ForecastWeather.RemoteForecastDataSource;
import com.example.elena.weatherconditionapplication.ForecastWeather.forecast;
import com.example.elena.weatherconditionapplication.ForecastWeather.forecastAdapter;
import com.example.elena.weatherconditionapplication.Utils.ConvertTemp;
import com.example.elena.weatherconditionapplication.Utils.GPSTracker;
import com.example.elena.weatherconditionapplication.Utils.GetDateTime;
import com.example.elena.weatherconditionapplication.Utils.UpdateLocation;
import com.example.elena.weatherconditionapplication.CurrentWeather.weather;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity implements WeatherContract.view {

    private RecyclerView rv_forecastList;
    private ImageView imageIcon;
    public static TextView dayTextview;
    private TextView condition;
    private TextView currentTemp;
    private TextView currentLocation;
    private AutoCompleteTextView search;
    private ProgressBar refreshProgressbar;
    private Button details;
    private Button refresh;
    private com.example.elena.weatherconditionapplication.ForecastWeather.forecastAdapter forecastAdapter;
    private RelativeLayout relativeLayout;


    //private ProgressDialog progressBar= new ProgressDialog(this);

    private CurrentWeather_Forecast_Presenter presenter;
    private RemoteCurrentWeatherDataSource remoteCurrentWeatherDataSource;
    private LocalCurrentWeatherDataSource localCurrentWeatherDataSource;
    private RemoteForecastDataSource remoteForecastDataSource;
    private LocalForacastDataSource localForacastDataSource;
    private String city;
    private String country;
    public static String city_name_for_request;
    private UpdateLocation updateLocation;
    private GPSTracker gpsTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        remoteCurrentWeatherDataSource = new RemoteCurrentWeatherDataSource(MainActivity.this);
        localCurrentWeatherDataSource = new LocalCurrentWeatherDataSource(MainActivity.this);
        remoteForecastDataSource = new RemoteForecastDataSource(MainActivity.this);
        localForacastDataSource = new LocalForacastDataSource(MainActivity.this);
        //updateLocation = new UpdateLocation(this);
        //gpsTracker=new GPSTracker(MainActivity.this);

        setupViews();
        //list of city for test
        String[] cityList = {"Tehran", "Mashhad", "Kerman", "Esfahan", "Tabriz", "Shiraz", "Hamedan", "Yazd", "Abadan", "Rasht", "Semnan", "Zahedan", "Sabzevar", "Karaj", "Urmia"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cityList);
        search.setAdapter(adapter);
        search.setThreshold(1);
        forecastAdapter = new forecastAdapter(MainActivity.this);
        rv_forecastList.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        rv_forecastList.setAdapter(forecastAdapter);
        presenter = new CurrentWeather_Forecast_Presenter(new CurrentWeatherDataSourceManager(remoteCurrentWeatherDataSource, localCurrentWeatherDataSource, MainActivity.this), new CurrentForecastDataSourceManager(remoteForecastDataSource, localForacastDataSource, MainActivity.this));
        presenter.attachView(this);




    }


    private void setupViews() {
      //  city = updateLocation.getcity();
      //  country = updateLocation.getcountry();
        if (city != null) city_name_for_request = city;
        else {

            city_name_for_request = "Tehran";

        }

        imageIcon = findViewById(R.id.imageVeiw_icon_main);
        dayTextview = findViewById(R.id.tv_day_main);
        condition = findViewById(R.id.tv_condition_main);
        currentTemp = findViewById(R.id.tv_temp_main);
        currentLocation = findViewById(R.id.tv_location_main);
        refreshProgressbar = findViewById(R.id.progressbar_refreshing_main);
        refresh = findViewById(R.id.btn_refresh_main);
        details = findViewById(R.id.btn_details_main);
        rv_forecastList = findViewById(R.id.rv_forecast_main);
        relativeLayout = findViewById(R.id.rl_bg_main);
        search = findViewById(R.id.autoCompleteTextView_city_main);

       /* currentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // updateLocation.setcountry();
              //  updateLocation.setcity();
                if (updateLocation.getcity() != null)
                    city_name_for_request = updateLocation.getcity();

                presenter.loadCurrentWeather();
                // presenter.loadCurrentForecast();


            }
        });*/

        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, DEtails_Currentweather_Activity.class);
                startActivity(intent);


            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh.setVisibility(View.GONE);
                refreshProgressbar.setVisibility(View.VISIBLE);
                presenter.onRefreshClick(currentLocation.getText().toString(), forecastAdapter);
                refreshProgressbar.setVisibility(View.GONE);
                refresh.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, "Weather updated", Toast.LENGTH_LONG).show();
            }
        });

        search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                city_name_for_request = (String) item;
                presenter.loadCurrentWeather();

                presenter.onRefreshClick(city_name_for_request, forecastAdapter);


            }
        });


    }


    @Override
    public void setProgressBarIndicator(boolean mustshow) {

    }

    @Override
    public void showWeather(weather weather) {

        dayTextview.setText(String.valueOf(GetDateTime.getdayname(weather.getDt())));
        currentLocation.setText(weather.getName());
        if (city != null && country != null) {
            currentLocation.setText(city + "  " + country);

        }

        condition.setText(weather.getMain().toString());
        currentTemp.setText(String.valueOf(ConvertTemp.convertToCelsius(weather.getTemp())) + " °C");
        setImageIcon(weather.getMain());


    }

    @Override
    public void showForecastList(List<forecast> forecastList) {
        forecastAdapter.setForecastList(forecastList);

    }


    private void setImageIcon(String condition) {
        switch (condition) {
            case "Sunny":
                Picasso.with(MainActivity.this).load(R.drawable.wc_sunny).into(imageIcon);
                relativeLayout.setBackground(getResources().getDrawable(R.drawable.sunny_w));


                break;
            case "Rain":
                Picasso.with(MainActivity.this).load(R.drawable.rain).into(imageIcon);
                relativeLayout.setBackground(getResources().getDrawable(R.drawable.rain_w));
                break;

            case "Clouds":
                Picasso.with(MainActivity.this).load(R.drawable.cloud).into(imageIcon);
                relativeLayout.setBackground(getResources().getDrawable(R.drawable.clouds_w));
                break;
            case "Mist":
                Picasso.with(MainActivity.this).load(R.drawable.mist).into(imageIcon);
                relativeLayout.setBackground(getResources().getDrawable(R.drawable.mist_w));
                break;
            case "Clear":
                Picasso.with(MainActivity.this).load(R.drawable.wc_sunny).into(imageIcon);
                relativeLayout.setBackground(getResources().getDrawable(R.drawable.clear_w));
                break;
            case "Haze":
                Picasso.with(MainActivity.this).load(R.drawable.haze).into(imageIcon);
                relativeLayout.setBackground(getResources().getDrawable(R.drawable.haze_w));
                break;
            case "Snow":
                Picasso.with(MainActivity.this).load(R.drawable.snow).into(imageIcon);
                relativeLayout.setBackground(getResources().getDrawable(R.drawable.snow_w));
                break;
            case "Drizzle":
                Picasso.with(MainActivity.this).load(R.drawable.drizzle).into(imageIcon);
                relativeLayout.setBackground(getResources().getDrawable(R.drawable.drizzle_w));
                break;
            case "Dust":
                Picasso.with(MainActivity.this).load(R.drawable.dust).into(imageIcon);
                relativeLayout.setBackground(getResources().getDrawable(R.drawable.dust_w));
                break;


        }

    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

    public static final String getDayName(final java.util.Date date, final java.util.Locale locale) {
        SimpleDateFormat df = new SimpleDateFormat("EEEE", locale);
        return df.format(date);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == GPSTracker.REQUEST_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] >= 0) {
                //locationManager.requestLocationUpdates(provider_info, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);


            }
        }
    }
}