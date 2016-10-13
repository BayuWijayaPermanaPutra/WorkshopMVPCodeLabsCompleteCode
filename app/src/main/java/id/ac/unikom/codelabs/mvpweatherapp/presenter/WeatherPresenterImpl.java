package id.ac.unikom.codelabs.mvpweatherapp.presenter;

import java.util.List;

import id.ac.unikom.codelabs.mvpweatherapp.model.Weather;
import id.ac.unikom.codelabs.mvpweatherapp.model.service.WeatherApi;
import id.ac.unikom.codelabs.mvpweatherapp.model.service.WeatherApiImpl;
import id.ac.unikom.codelabs.mvpweatherapp.view.MainActivity;
import id.ac.unikom.codelabs.mvpweatherapp.view.ViewMainActivity;

/**
 * Created by Bayu WPP on 10/13/2016.
 */

public class WeatherPresenterImpl extends BasePresenter implements WeatherPresenter {
    private final ViewMainActivity mainView;
    private final WeatherApiImpl weatherApi;

    public WeatherPresenterImpl(ViewMainActivity view, WeatherApiImpl mWeatherApi) {
        mainView = view;
        weatherApi = mWeatherApi;
    }

    @Override
    public void loadWeatherData() {
        mainView.showProgress();
        weatherApi.getWeathers(new WeatherApi.WeatherServiceCallback<List<Weather>>() {
            @Override
            public void onSuccess(List<Weather> weathers) {
                mainView.hideProgress();
                mainView.showWeathers(weathers);
            }

            @Override
            public void onFailure() {
                mainView.hideProgress();
                mainView.showConnectionError();
            }
        });

    }

    @Override
    public void itemClick(Weather cuaca) {
        mainView.showWeatherClickedMessage(cuaca);
    }
}
