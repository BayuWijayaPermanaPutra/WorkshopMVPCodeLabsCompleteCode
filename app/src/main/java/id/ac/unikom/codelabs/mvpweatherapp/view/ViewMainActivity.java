package id.ac.unikom.codelabs.mvpweatherapp.view;

import java.util.List;

import id.ac.unikom.codelabs.mvpweatherapp.model.Weather;

/**
 * Created by Bayu WPP on 10/14/2016.
 */

public interface ViewMainActivity {
    void showProgress();
    void hideProgress();
    void showWeatherClickedMessage(Weather cuaca);
    void showWeathers(List<Weather> cuacaList);
    void showConnectionError();
}
