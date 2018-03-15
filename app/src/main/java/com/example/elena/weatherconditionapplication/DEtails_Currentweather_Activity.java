package com.example.elena.weatherconditionapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.elena.weatherconditionapplication.CurrentWeather.CurrentWeatherSharedPrefManager;
import com.example.elena.weatherconditionapplication.Utils.ConvertTemp;
import com.example.elena.weatherconditionapplication.Utils.GetDateTime;
import com.example.elena.weatherconditionapplication.CurrentWeather.weather;
import com.squareup.picasso.Picasso;

public class DEtails_Currentweather_Activity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView temp_tv;
    private TextView condition_tv;
    private TextView location_tv;
    private TextView datetime_tv;
    private TextView description_tv;
    private TextView maxtemp_tv;
    private TextView mintemp_tv;
    private TextView humidity_tv;
    private TextView pressure_tv;
    private TextView windspeed_tv;
    private TextView winddegry_tv;

    private ImageView description_image;
    private ImageView maxtemp_image;
    private ImageView mintemp_image;
    private ImageView humidity_image;
    private ImageView pressure_image;
    private ImageView windspeed_image;
    private ImageView winddegry_image;
    private com.example.elena.weatherconditionapplication.CurrentWeather.weather weather;
    private RelativeLayout relativeLayout;
    private CurrentWeatherSharedPrefManager currentWeatherSharedPrefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        currentWeatherSharedPrefManager = new CurrentWeatherSharedPrefManager(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details__currentweather_);
        weather = currentWeatherSharedPrefManager.getweather();
        setupViews();
        setValues(weather);


    }

    private void setValues(weather weather) {
        if (weather != null) {
            temp_tv.setText(weather.getMain().toString());
            condition_tv.setText(String.valueOf(ConvertTemp.convertToCelsius(weather.getTemp())) + " °C");
            location_tv.setText(weather.getName().toString());
            datetime_tv.setText(MainActivity.dayTextview.getText().toString());
            description_tv.setText("Weather description: " + weather.getDescription().toString());
            maxtemp_tv.setText("Max_Temp: " + String.valueOf(ConvertTemp.convertToCelsius(weather.getTemp_max())) + " °C");
            mintemp_tv.setText("Min_Temp: " + String.valueOf(ConvertTemp.convertToCelsius(weather.getTemp_min())) + " °C");
            humidity_tv.setText(String.valueOf(weather.getHumidity()));
            pressure_tv.setText(String.valueOf(weather.getPressure()));
            windspeed_tv.setText(String.valueOf(weather.getWindSpeed()));
            winddegry_tv.setText(String.valueOf(weather.getWindDegree()));
        }

    }

    private void setupViews() {
        Toolbar toolbar = findViewById(R.id.toolbar_fileManagement);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        condition_tv = findViewById(R.id.tv_condition_details);
        temp_tv = findViewById(R.id.tv_temp_details);
        location_tv = findViewById(R.id.tv_location_details);
        datetime_tv = findViewById(R.id.tv_date_details);
        description_tv = findViewById(R.id.tv_description_details);
        maxtemp_tv = findViewById(R.id.tv_maxtemp_details);
        mintemp_tv = findViewById(R.id.tv_mintemp_details);
        humidity_tv = findViewById(R.id.tv_humidity_details);
        pressure_tv = findViewById(R.id.tv_pressure_details);
        windspeed_tv = findViewById(R.id.tv_windspeed_details);
        winddegry_tv = findViewById(R.id.tv_winddegry_details);

        description_image = findViewById(R.id.imageview_description_details);
        //  Picasso.with(this).load(R.drawable.)
        maxtemp_image = findViewById(R.id.imageVeiw_maxxtemp_details);
        Picasso.with(this).load(R.drawable.red_thermometer).into(maxtemp_image);


        mintemp_image = findViewById(R.id.imageVeiw_mintemp_details);
        Picasso.with(this).load(R.drawable.blue_thermometer).into(mintemp_image);

        humidity_image = findViewById(R.id.imageVeiw_humidity_details);
        Picasso.with(this).load(R.drawable.humidity).into(humidity_image);

        pressure_image = findViewById(R.id.imageVeiw_pressure_details);
        Picasso.with(this).load(R.drawable.pressure).into(pressure_image);

        windspeed_image = findViewById(R.id.imageVeiw_windspeed_details);
        Picasso.with(this).load(R.drawable.windspeed).into(windspeed_image);

        winddegry_image = findViewById(R.id.imageVeiw_winddegry_details);
        Picasso.with(this).load(R.drawable.windspeed).into(winddegry_image);

        relativeLayout = findViewById(R.id.rl_bg_details);
        setbackground(weather.getMain());

    }

    private void setbackground(String condition) {
        switch (condition) {
            case "Sunny":
                relativeLayout.setBackground(getResources().getDrawable(R.drawable.sunny_w));
                break;
            case "Rain":
                relativeLayout.setBackground(getResources().getDrawable(R.drawable.rain_w));
                break;
            case "Clouds":
                relativeLayout.setBackground(getResources().getDrawable(R.drawable.clouds_w));
                break;
            case "Mist":
                relativeLayout.setBackground(getResources().getDrawable(R.drawable.mist_w));
                break;
            case "Clear":
                relativeLayout.setBackground(getResources().getDrawable(R.drawable.clear_w));
                break;
            case "Haze":
                relativeLayout.setBackground(getResources().getDrawable(R.drawable.haze_w));
                break;
            case "Snow":
                relativeLayout.setBackground(getResources().getDrawable(R.drawable.snow_w));
                break;
            case "Drizzle":
                relativeLayout.setBackground(getResources().getDrawable(R.drawable.drizzle_w));
                break;
            case "Dust":
                relativeLayout.setBackground(getResources().getDrawable(R.drawable.dust_w));
                break;

        }
        }
    }
