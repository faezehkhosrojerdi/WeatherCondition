package com.example.elena.weatherconditionapplication.ForecastWeather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elena.weatherconditionapplication.R;
import com.example.elena.weatherconditionapplication.Utils.ConvertTemp;
import com.example.elena.weatherconditionapplication.Utils.GetDateTime;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elena on 3/11/2018.
 */

public class forecastAdapter extends RecyclerView.Adapter<forecastAdapter.forecastViewHolder> {
        private List<forecast> forecastList = new ArrayList<>();
        private Context context;

        public forecastAdapter(Context context){
            this.context=context;
        }



        @Override
        public forecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new forecastViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_forecast, parent, false
            ));
        }

        @Override
        public void onBindViewHolder(forecastViewHolder holder, int position) {
            holder.bindforecast(forecastList.get(position));
        }

        public void setForecastList(List<forecast> forecastList) {
            this.forecastList=forecastList;
            notifyDataSetChanged();
        }
        public  void RemoveAll(){
            forecastList.clear();
        }


        @Override
        public int getItemCount() {
            return forecastList.size();
        }

        public  class forecastViewHolder extends RecyclerView.ViewHolder {
            private ImageView imageView;
            private TextView day;
            private TextView main;
            private TextView mintemp;
            private TextView maxtemp;


            public forecastViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imgviiew_icon_forecastitem);
                day=itemView.findViewById(R.id.tv_day_forecastitem);
                main=itemView.findViewById(R.id.tv_main_forecastitem);
                mintemp=itemView.findViewById(R.id.tv_mintemp_forecastitem);
                maxtemp=itemView.findViewById(R.id.tv_maxtemp_forecastitem);

            }

            public void bindforecast(final forecast forecast) {
                day.setText(String.valueOf(GetDateTime.getdayname(forecast.getDt())));
                main.setText(String.valueOf(forecast.getMain()));
                mintemp.setText(String.valueOf(ConvertTemp.convertToCelsius(forecast.getMintemp()))+  " °C");
                maxtemp.setText(String.valueOf(ConvertTemp.convertToCelsius(forecast.getMaxtemp()))+  " °C");
                setIcon(String.valueOf(forecast.getMain()));



            }

            public void setIcon(String condition){
                switch (condition) {
                    case "Sunny":

                        Picasso.with(context).load(R.drawable.wc_sunny).into(imageView);
                        break;
                    case "Rain":
                        Picasso.with(context).load(R.drawable.rain).into(imageView);
                        break;

                    case "Clouds":
                        Picasso.with(context).load(R.drawable.cloud).into(imageView);
                        break;
                    case "Mist":
                        Picasso.with(context).load(R.drawable.mist).into(imageView);
                        break;
                    case "Clear":
                        Picasso.with(context).load(R.drawable.wc_sunny).into(imageView);
                        break;
                    case "Haze":
                        Picasso.with(context).load(R.drawable.haze).into(imageView);
                        break;
                    case "Snow":
                        Picasso.with(context).load(R.drawable.snow).into(imageView);
                        break;
                    case "Drizzle":
                        Picasso.with(context).load(R.drawable.drizzle).into(imageView);
                        break;




                }
            }


        }
    }

