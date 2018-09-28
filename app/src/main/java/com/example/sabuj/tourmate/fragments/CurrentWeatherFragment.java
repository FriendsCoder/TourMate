package com.example.sabuj.tourmate.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sabuj.tourmate.R;
import com.example.sabuj.tourmate.model_current_weather.CurrentWeather;
import com.example.sabuj.tourmate.weather_api_collection.RectrofitClient;
import com.example.sabuj.tourmate.weather_api_collection.WeatherApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.example.sabuj.tourmate.WeatherActivity.currentWeather;
import static com.example.sabuj.tourmate.WeatherActivity.tempFormat;

public class CurrentWeatherFragment extends Fragment {
    WeatherApi weatherApi;
    Calendar calendar;
    ImageView icon;
    TextView tvDefaultTemp, tvDefaultDate, tvDefaultDay, tvDefaultLocation,
            tvDefaultMinimumTemp, tvDefaultMaxTemp, tvDefaultHumadity,
            tvDefaultPressare, tvDefaultSunrise, tvDefaultSunset, tvDescription;
    public static String API_KEY = "f292fab96ddbc02283f3f81cef11a29b";

    public CurrentWeatherFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_current_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialization(view);
        weatherApi = RectrofitClient.getRetrofitClient().create(WeatherApi.class);
        calendar = Calendar.getInstance();
    }

    private void initialization(View view) {
        tvDefaultTemp = view.findViewById(R.id.tvDefaultWeather);
        tvDefaultDate = view.findViewById(R.id.tvDefaultDate);
        tvDefaultDay = view.findViewById(R.id.tvDefaultDay);
        tvDefaultLocation = view.findViewById(R.id.tvDefaultLocation);
        tvDefaultMinimumTemp = view.findViewById(R.id.tvDefaultMinimumWeather);
        tvDefaultMaxTemp = view.findViewById(R.id.tvDefaultMaximumWeather);
        tvDefaultHumadity = view.findViewById(R.id.tvDefaultHumidityPercent);
        tvDefaultPressare = view.findViewById(R.id.tvDefaultPressure);
        tvDefaultSunrise = view.findViewById(R.id.tvDefaultSunriseTime);
        tvDefaultSunset = view.findViewById(R.id.tvDefaultSunsetTime);
        tvDescription = view.findViewById(R.id.tvDefaultWeatherDescription);
        icon = view.findViewById(R.id.ivIcon);
    }

    @Override
    public void onResume() {
        super.onResume();
        setAll(currentWeather);

    }

    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        String date = DateFormat.format("hh:mm a", cal).toString();
        return date;
    }

    private void setAll(CurrentWeather currentWeather) {
        String temp = null, minTemp = null, maxTemp = null;
        if (tempFormat == 1) {
            temp = String.format("%.0f", currentWeather.getMain().getTempCentigrate()) + "\u2103";
            minTemp = String.format("%.0f", currentWeather.getMain().getTempMinCentigrate()) + "\u2103";
            maxTemp = String.format("%.0f", currentWeather.getMain().getTempMaxCentigrate()) + "\u2103";
        }
        if (tempFormat == 2) {
            temp = String.format("%.0f", currentWeather.getMain().getTempFahrenheit()) + "\u2109";
            minTemp = String.format("%.0f", currentWeather.getMain().getTempMinFahrenheit()) + "\u2109";
            maxTemp = String.format("%.0f", currentWeather.getMain().getTempMaxFahrenheit()) + "\u2109";
        }

        int tempInC = (int) (currentWeather.getMain().getTemp() - 273);
        tvDescription.setText(currentWeather.getWeather().get(0).getDescription().toString());
        if (tvDescription.getText().toString().contains("ain")) {
            icon.setImageResource(R.drawable.rain_icon);
        } else if (tvDescription.getText().toString().contains("aze")) {
            icon.setImageResource(R.drawable.haze_icon);
        } else if (tvDescription.getText().toString().contains("loud")) {
            icon.setImageResource(R.drawable.cloud_icon);
        } else {
            icon.setImageResource(R.drawable.sunny);
        }
        tvDefaultLocation.setText(currentWeather.getName().toString());
        tvDefaultTemp.setText(temp);
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        tvDefaultDate.setText(formattedDate);
        String weekday_name = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(System.currentTimeMillis());
        tvDefaultDay.setText(weekday_name);
        tvDefaultMinimumTemp.setText(minTemp);
        tvDefaultMaxTemp.setText(maxTemp);
        tvDefaultHumadity.setText("" + currentWeather.getMain().getHumidity() + "%");
        tvDefaultPressare.setText("" + currentWeather.getMain().getPressure() + "hpa");

        tvDefaultSunrise.setText(getDate(currentWeather.getSys().getSunrise().longValue() * 1000));
        tvDefaultSunset.setText(getDate(currentWeather.getSys().getSunset().longValue() * 1000));
    }
}
