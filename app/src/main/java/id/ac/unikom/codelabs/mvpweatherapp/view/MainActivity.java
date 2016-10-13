package id.ac.unikom.codelabs.mvpweatherapp.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import id.ac.unikom.codelabs.mvpweatherapp.R;
import id.ac.unikom.codelabs.mvpweatherapp.adapter.WeatherAdapter;
import id.ac.unikom.codelabs.mvpweatherapp.model.Weather;
import id.ac.unikom.codelabs.mvpweatherapp.model.service.WeatherApiImpl;
import id.ac.unikom.codelabs.mvpweatherapp.presenter.WeatherPresenterImpl;

public class MainActivity extends AppCompatActivity implements ViewMainActivity, WeatherAdapter.WeatherItemListener {
    private WeatherPresenterImpl mPresenter;
    private WeatherAdapter weatherAdapter;
    private SwipeRefreshLayout mSrl;
    //private ProgressBar progressBar;
    private RecyclerView rvWeatherList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new WeatherPresenterImpl(this,new WeatherApiImpl());
        initView();
        setupRecyclerView();
        setupSwipeRefresh();
    }

    private void setupSwipeRefresh() {
        mSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadWeatherData();
            }
        });
    }

    private void setupRecyclerView() {
        weatherAdapter = new WeatherAdapter(this, this);
        rvWeatherList.setLayoutManager(new LinearLayoutManager(this));
        rvWeatherList.setAdapter(weatherAdapter);
    }

    private void initView() {
        mSrl = (SwipeRefreshLayout) findViewById(R.id.swipe_main);
        rvWeatherList = (RecyclerView) findViewById(R.id.recyclerview_main);
        //progressBar = (ProgressBar) findViewById(R.id.progressbar_main);
    }

    @Override
    public void onWeatherItemClick(Weather item) {
        mPresenter.itemClick(item);
    }

    @Override
    public void showProgress() {
        if(!mSrl.isRefreshing()) {

            // make sure setRefreshing() is called after the layout done everything else
            mSrl.post(new Runnable() {
                @Override
                public void run() {
                    mSrl.setRefreshing(true);
                }
            });
        }
    }

    @Override
    public void hideProgress() {
        if(mSrl.isRefreshing()) {
            mSrl.setRefreshing(false);
        }
        //progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showWeatherClickedMessage(Weather cuaca) {
        Toast.makeText(this, "Kota " + cuaca.getCityName()+ " dengan temperatur "+ cuaca.getTemperature()+"\u00B0C", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showWeathers(List<Weather> cuacaList) {
        weatherAdapter.replaceData(cuacaList);
    }

    @Override
    public void showConnectionError() {
        Toast.makeText(MainActivity.this, "Failed to connect, please check your connection and try again!", Toast.LENGTH_LONG).show();
    }
}
