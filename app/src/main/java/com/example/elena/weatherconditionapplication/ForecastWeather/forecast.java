package com.example.elena.weatherconditionapplication.ForecastWeather;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Elena on 3/11/2018.
 */

@Entity(tableName = "tbl_forecast")
public class forecast implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "dt")
    private  long dt;
    @ColumnInfo(name ="mintemp" )
    private  double mintemp;
    @ColumnInfo(name = "maxtemp")
    private  double maxtemp;
    @ColumnInfo(name = "main")
    private  String main;

    protected forecast(Parcel in) {
        id = in.readInt();
        dt = in.readLong();
        mintemp = in.readDouble();
        maxtemp = in.readDouble();
        main = in.readString();
    }

    public forecast(){}

    public static final Creator<forecast> CREATOR = new Creator<forecast>() {
        @Override
        public forecast createFromParcel(Parcel in) {
            return new forecast(in);
        }

        @Override
        public forecast[] newArray(int size) {
            return new forecast[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




    public double getMintemp() {
        return mintemp;
    }

    public void setMintemp(double mintemp) {
        this.mintemp = mintemp;
    }

    public double getMaxtemp() {
        return maxtemp;
    }

    public void setMaxtemp(double maxtemp) {
        this.maxtemp = maxtemp;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeLong(dt);
        dest.writeDouble(mintemp);
        dest.writeDouble(maxtemp);
        dest.writeString(main);
    }
}
