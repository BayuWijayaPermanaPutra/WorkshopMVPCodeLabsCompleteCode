package id.ac.unikom.codelabs.mvpweatherapp.model.service;

import java.util.List;

import id.ac.unikom.codelabs.mvpweatherapp.model.Weather;
import retrofit2.Callback;

/**
 * Created by Bayu WPP on 10/13/2016.
 */

public interface WeatherApi {
    interface WeatherServiceCallback<B> {
        void onSuccess(B weathers);
        void onFailure();
    }
    void getWeathers(WeatherServiceCallback<List<Weather>> callbackWeathers);
}
