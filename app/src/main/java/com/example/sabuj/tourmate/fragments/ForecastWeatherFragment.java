package com.example.sabuj.tourmate.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sabuj.tourmate.R;
import com.example.sabuj.tourmate.model_forcast_weather.ForecastWeather;
import com.example.sabuj.tourmate.weather_api_collection.RectrofitClient;
import com.example.sabuj.tourmate.weather_api_collection.WeatherApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import static com.example.sabuj.tourmate.WeatherActivity.forecastWeather;
import static com.example.sabuj.tourmate.WeatherActivity.tempFormat;

public class ForecastWeatherFragment extends Fragment {
    TextView tvDayFirstMinForecast, tvFirstDate, tvDayFirstMaxForecast,
            tvDaySecondMinForecast, tvSecondDate, tvDaySecondMaxForecast,
            tvDayThird, tvDayThirdMinForecast, tvThirdDate, tvDayThirdMaxForecast,
            tvDayFourth, tvDayFourthMinForecast, tvFourthDate, tvDayFourthMaxForecast,
            tvDayFifth, tvDayFifthMinForecast, tvFifthDate, tvDayFifthMaxForecast,
            tvlocation;
    WeatherApi weatherApi;

    public ForecastWeatherFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forecast_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialization(view);
        weatherApi = RectrofitClient.getRetrofitClient().create(WeatherApi.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        setForecastTemp(forecastWeather);

    }

    private void setForecastTemp(ForecastWeather forecastWeather) {
        tvlocation.setText(forecastWeather.getCity().getName());
        setToday(forecastWeather);
        setTomorrow(forecastWeather);
        setThirdDay(forecastWeather);
        setFourthDay(forecastWeather);
        setFifthDay(forecastWeather);
    }

    private void setFifthDay(ForecastWeather forecastWeather) {
        String temp = null, minTemp = null, maxTemp = null;
        String weekday_name = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(forecastWeather.getList().get(32).getDt().longValue() * 1000);
        if (tempFormat == 1) {

            minTemp = String.format("%.0f", forecastWeather.getList().get(32).getMain().getTempMinCentigrate()) + "\u2103";
            maxTemp = String.format("%.0f", forecastWeather.getList().get(32).getMain().getTempMaxCentigrate()) + "\u2103";
        }
        if (tempFormat == 2) {

            minTemp = String.format("%.0f", forecastWeather.getList().get(32).getMain().getTempMinFarenheit()) + "\u2109";
            maxTemp = String.format("%.0f", forecastWeather.getList().get(32).getMain().getTempMaxFarenheit()) + "\u2109";
        }
        tvDayFifthMaxForecast.setText(maxTemp);
        tvDayFifthMinForecast.setText(minTemp);

        String formattedDate = getDate(forecastWeather.getList().get(32).getDt().longValue() * 1000);
        tvFifthDate.setText(formattedDate);
        tvDayFifth.setText(weekday_name);
    }

    private void setFourthDay(ForecastWeather forecastWeather) {
        String temp = null, minTemp = null, maxTemp = null;
        String weekday_name = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(forecastWeather.getList().get(24).getDt().longValue() * 1000);
        if (tempFormat == 1) {

            minTemp = String.format("%.0f", forecastWeather.getList().get(24).getMain().getTempMinCentigrate()) + "\u2103";
            maxTemp = String.format("%.0f", forecastWeather.getList().get(24).getMain().getTempMaxCentigrate()) + "\u2103";
        }
        if (tempFormat == 2) {

            minTemp = String.format("%.0f", forecastWeather.getList().get(24).getMain().getTempMinFarenheit()) + "\u2109";
            maxTemp = String.format("%.0f", forecastWeather.getList().get(24).getMain().getTempMaxFarenheit()) + "\u2109";
        }
        tvDayFourthMaxForecast.setText(maxTemp);
        tvDayFourthMinForecast.setText(minTemp);

        String formattedDate = getDate(forecastWeather.getList().get(24).getDt().longValue() * 1000);
        tvFourthDate.setText(formattedDate);
        tvDayFourth.setText(weekday_name);
    }

    private void setThirdDay(ForecastWeather forecastWeather) {
        String temp = null, minTemp = null, maxTemp = null;
        String weekday_name = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(forecastWeather.getList().get(16).getDt().longValue() * 1000);
        if (tempFormat == 1) {

            minTemp = String.format("%.0f", forecastWeather.getList().get(16).getMain().getTempMinCentigrate()) + "\u2103";
            maxTemp = String.format("%.0f", forecastWeather.getList().get(16).getMain().getTempMaxCentigrate()) + "\u2103";
        }
        if (tempFormat == 2) {

            minTemp = String.format("%.0f", forecastWeather.getList().get(16).getMain().getTempMinFarenheit()) + "\u2109";
            maxTemp = String.format("%.0f", forecastWeather.getList().get(16).getMain().getTempMaxFarenheit()) + "\u2109";
        }
        tvDayThirdMaxForecast.setText(maxTemp);
        tvDayThirdMinForecast.setText(minTemp);
        String formattedDate = getDate(forecastWeather.getList().get(16).getDt().longValue() * 1000);
        tvThirdDate.setText(formattedDate);
        tvDayThird.setText(weekday_name);
    }

    private void setTomorrow(ForecastWeather forecastWeather) {
        String temp = null, minTemp = null, maxTemp = null;
        if (tempFormat == 1) {
            //temp=String.format("%.2f",forecastWeather.getList().get(0).getMain().getTempMCentigrate())+"\u2103";
            minTemp = String.format("%.0f", forecastWeather.getList().get(8).getMain().getTempMinCentigrate()) + "\u2103";
            maxTemp = String.format("%.0f", forecastWeather.getList().get(8).getMain().getTempMaxCentigrate()) + "\u2103";
        }
        if (tempFormat == 2) {
            // temp=String.format("%.2f",forecastWeather.getMain().getTempFahrenheit())+"\u2109";
            minTemp = String.format("%.0f", forecastWeather.getList().get(8).getMain().getTempMinFarenheit()) + "\u2109";
            maxTemp = String.format("%.0f", forecastWeather.getList().get(8).getMain().getTempMaxFarenheit()) + "\u2109";
        }
        tvDaySecondMaxForecast.setText(maxTemp);
        tvDaySecondMinForecast.setText(minTemp);

        String formattedDate = getDate(forecastWeather.getList().get(8).getDt().longValue() * 1000);
        tvSecondDate.setText(formattedDate);
    }

    private void setToday(ForecastWeather forecastWeather) {
        String temp = null, minTemp = null, maxTemp = null;
        String weekday_name = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(forecastWeather.getList().get(0).getDt().longValue() * 1000);
        if (tempFormat == 1) {
            minTemp = String.format("%.0f", forecastWeather.getList().get(0).getMain().getTempMinCentigrate()) + "\u2103";
            maxTemp = String.format("%.0f", forecastWeather.getList().get(0).getMain().getTempMaxCentigrate()) + "\u2103";
        }
        if (tempFormat == 2) {
            minTemp = String.format("%.0f", forecastWeather.getList().get(0).getMain().getTempMinFarenheit()) + "\u2109";
            maxTemp = String.format("%.0f", forecastWeather.getList().get(0).getMain().getTempMaxFarenheit()) + "\u2109";
        }

        tvDayFirstMaxForecast.setText(maxTemp);
        tvDayFirstMinForecast.setText(minTemp);
        String formattedDate = getDate(forecastWeather.getList().get(0).getDt().longValue() * 1000);
        tvFirstDate.setText(formattedDate);
    }

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = DateFormat.format("dd-MMM-yyyy", cal).toString();
        return date;
    }

    private void initialization(View view) {
        tvDayFirstMinForecast = view.findViewById(R.id.tvDayFirstMinForecast);
        tvFirstDate = view.findViewById(R.id.tvFirstDate);
        tvDayFirstMaxForecast = view.findViewById(R.id.tvDayFirstMaxForecast);

        tvDaySecondMinForecast = view.findViewById(R.id.tvDaySecondMinForecast);
        tvSecondDate = view.findViewById(R.id.tvSecondDate);
        tvDaySecondMaxForecast = view.findViewById(R.id.tvDaySecondMaxForecast);

        tvDayThird = view.findViewById(R.id.tvDayThird);
        tvDayThirdMinForecast = view.findViewById(R.id.tvDayThirdMinForecast);
        tvThirdDate = view.findViewById(R.id.tvThirdDate);
        tvDayThirdMaxForecast = view.findViewById(R.id.tvDayThirdMaxForecast);

        tvDayFourth = view.findViewById(R.id.tvDayFourth);
        tvDayFourthMinForecast = view.findViewById(R.id.tvDayFourthMinForecast);
        tvFourthDate = view.findViewById(R.id.tvFourthDate);
        tvDayFourthMaxForecast = view.findViewById(R.id.tvDayFourthMaxForecast);

        tvDayFifth = view.findViewById(R.id.tvDayFifth);
        tvDayFifthMinForecast = view.findViewById(R.id.tvDayFifthMinForecast);
        tvFifthDate = view.findViewById(R.id.tvFifthDate);
        tvDayFifthMaxForecast = view.findViewById(R.id.tvDayFifthMaxForecast);

        tvlocation = view.findViewById(R.id.tvLocation);
    }
}
