package id.ac.unikom.codelabs.mvpweatherapp.presenter;

import id.ac.unikom.codelabs.mvpweatherapp.model.Weather;

/**
 * Created by Bayu WPP on 10/13/2016.
 */

public interface WeatherPresenter {
    void loadWeatherData();
    void itemClick(Weather cuaca);
}
