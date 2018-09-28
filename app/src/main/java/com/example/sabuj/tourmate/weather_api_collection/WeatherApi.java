package com.example.sabuj.tourmate.weather_api_collection;

import com.example.sabuj.tourmate.model_current_weather.CurrentWeather;
import com.example.sabuj.tourmate.model_forcast_weather.ForecastWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface WeatherApi {

   // @GET("forecast?q=dhaka,bangladesh&appid=f292fab96ddbc02283f3f81cef11a29b")
    @GET("weather")
    Call<CurrentWeather> getCurrentWeather(@Query("q") String location, @Query("appid") String key);
    @GET("forecast")
    Call<ForecastWeather> getForcastWeather(@Query("q") String location, @Query("appid") String key);
}
