package com.example.sabuj.tourmate.weather_api_collection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RectrofitClient {
    public static Retrofit getRetrofitClient(){
        return  new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
