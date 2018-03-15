package com.example.elena.weatherconditionapplication.Utils;

import android.content.Context;

import com.example.elena.weatherconditionapplication.R;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Elena on 3/12/2018.
 */

public class GetDateTime {
    public static String Getday(long d) {
        Timestamp timestamp = new Timestamp(d);
        String day = (new SimpleDateFormat("EEEE")).format(timestamp.getTime());
        return day;

    }



    public static String getdayname(long t) {
        long timestamp = t * 1000l;
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp);
        int daynum = cal.get(Calendar.DAY_OF_WEEK);
//System.out.println("current month is "+cal.get(Calendar.MONTH));
        switch (daynum) {
            case 1:
                return "Sunday";
            case 2:
                return "Monday";
            case 3:
                return "Tuesday";
            case 4:
                return "Wednesday";
            case 5:
                return "Thursday";
            case 6:
                return "Friday";
            case 7:
                return "Saturday";
        }
        return null;
    }
}