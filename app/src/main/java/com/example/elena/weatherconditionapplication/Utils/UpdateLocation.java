package com.example.elena.weatherconditionapplication.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Elena on 3/13/2018.
 */

public class UpdateLocation {
    private Context context;
    private GPSTracker gpsTracker;
    private String city;
    private  String country;

     public UpdateLocation(Context context){
         this.context=context;
        setcity();
        setcountry();

     }

     public void setcity(){
         gpsTracker=new GPSTracker(context);
         if (gpsTracker.getIsGPSTrackingEnabled())
         { city = gpsTracker.getLocality(context);
         }
         else
         {

             gpsTracker.showSettingsAlert();
             city=null;
         }

     }

    public void setcountry(){
        gpsTracker=new GPSTracker(context);
        if (gpsTracker.getIsGPSTrackingEnabled())
        {country = gpsTracker.getCountryName(context);
        }
        else
        {

            gpsTracker.showSettingsAlert();
            country=null;
        }

    }

    public String getcity(){
        if (city!=null)
        return city;
        else return null;
    }

    public String getcountry(){
        if (country!=null)
            return country;
        else return null;
    }
    // String stringLatitude = String.valueOf(gpsTracker.latitude);
    // String stringLongitude = String.valueOf(gpsTracker.longitude);
    // String postalCode = gpsTracker.getPostalCode(this);
    //String addressLine = gpsTracker.getAddressLine(this);


}
