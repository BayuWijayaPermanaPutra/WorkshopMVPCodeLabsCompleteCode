package id.ac.unikom.codelabs.mvpweatherapp.model.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import id.ac.unikom.codelabs.mvpweatherapp.model.Weather;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Bayu WPP on 10/13/2016.
 */

public interface ApiService {
    String BASE_URL = "http://api.openweathermap.org/";

    @GET("data/2.5/group")
    Call<Weather> getWeather(@Query("id") String id, @Query("units") String units, @Query("APPID") String APPID);

    class factory {
        public static ApiService create() {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.connectTimeout(10, TimeUnit.SECONDS);
            builder.writeTimeout(10, TimeUnit.SECONDS);

            OkHttpClient client = builder.build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit.create(ApiService.class);
        }
    }
}
