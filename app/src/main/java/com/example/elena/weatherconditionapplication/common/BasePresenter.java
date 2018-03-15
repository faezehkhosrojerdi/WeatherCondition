package com.example.elena.weatherconditionapplication.common;

/**
 * Created by Elena on 3/10/2018.
 */

public interface BasePresenter<V> {
    void attachView( V view);
    void detach();
}
