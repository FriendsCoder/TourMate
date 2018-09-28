package com.example.sabuj.tourmate;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sabuj.tourmate.fragments.CurrentWeatherFragment;
import com.example.sabuj.tourmate.fragments.ForecastWeatherFragment;
import com.example.sabuj.tourmate.model_current_weather.CurrentWeather;
import com.example.sabuj.tourmate.model_forcast_weather.ForecastWeather;
import com.example.sabuj.tourmate.weather_api_collection.RectrofitClient;
import com.example.sabuj.tourmate.weather_api_collection.WeatherApi;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.sabuj.tourmate.fragments.CurrentWeatherFragment.API_KEY;

public class WeatherActivity extends AppCompatActivity {
    Toolbar toolbar;
    WeatherApi weatherApi;
    TextView tvCurrentTemp;
    public static int tempFormat = 1;
    public static int tabIndex = 1;
    public static CurrentWeather currentWeather;
    public static ForecastWeather forecastWeather;
    public static String location = "dhaka,bangladesh";
    String queryLocation = null;
    boolean doubleBackToExitPressedOnce = false;
    public static FragmentTransaction transaction;
    MaterialSearchView searchView;
    String[] list;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initialization();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvCurrentTemp.setVisibility(View.GONE);
        weatherApi = RectrofitClient.getRetrofitClient().create(WeatherApi.class);

        tabLayout.addTab(tabLayout.newTab().setText("Current Weather"));
        tabLayout.addTab(tabLayout.newTab().setText("Forecast Weather"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        transaction = getSupportFragmentManager().beginTransaction();
        callSearch();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tvCurrentTemp.setText(tab.getText().toString());
                if (tab.getPosition() == 0) {
                    tabIndex = 1;
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    CurrentWeatherFragment currentWeatherFragment = new CurrentWeatherFragment();
                    transaction.replace(R.id.weatherFragmentLayout, currentWeatherFragment);
                    transaction.commit();
                } else {
                    tabIndex = 2;
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    ForecastWeatherFragment forecastWeatherFragment = new ForecastWeatherFragment();
                    transaction.replace(R.id.weatherFragmentLayout, forecastWeatherFragment);
                    transaction.commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        callDefaultWeather();
    }

    private void callDefaultWeather() {
        Call<CurrentWeather> weatherCall = weatherApi.getCurrentWeather(location, API_KEY);
        weatherCall.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                CurrentWeather weather = response.body();
                if (weather != null) {
                    currentWeather = weather;
                    if (tabIndex == 1) {
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        CurrentWeatherFragment currentWeatherFragment = new CurrentWeatherFragment();
                        transaction.replace(R.id.weatherFragmentLayout, currentWeatherFragment);
                        transaction.commit();
                    }
                } else {
                    location = queryLocation;
                    Toast.makeText(WeatherActivity.this, "Location Not Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {

            }
        });

        Call<ForecastWeather> forecastWeatherCall = weatherApi.getForcastWeather(location, API_KEY);
        forecastWeatherCall.enqueue(new Callback<ForecastWeather>() {
            @Override
            public void onResponse(Call<ForecastWeather> call, Response<ForecastWeather> response) {
                ForecastWeather weather = response.body();
                if (weather != null) {
                    forecastWeather = weather;
                    if (tabIndex == 2) {
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        ForecastWeatherFragment forecastWeatherFragment = new ForecastWeatherFragment();
                        transaction.replace(R.id.weatherFragmentLayout, forecastWeatherFragment);
                        transaction.commit();
                    }
                } else {
                    location = queryLocation;
                    Toast.makeText(WeatherActivity.this, "Location Not Found", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ForecastWeather> call, Throwable t) {

            }
        });
    }


    private void callSearch() {
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                queryLocation = location;
                location = query + ",bangladesh";
                callDefaultWeather();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
                searchView.showSuggestions();
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.weather_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            case R.id.myLocationID:
                location = "dhaka,bangladesh";
                callDefaultWeather();
                return true;
            case R.id.celciousId:
                tempFormat = 1;
                callDefaultWeather();
                return true;
            case R.id.farenhaitId:
                tempFormat = 2;
                callDefaultWeather();
                return true;
            case R.id.refreshID:
                callDefaultWeather();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void initialization() {
        toolbar = findViewById(R.id.toolbar);
        tvCurrentTemp = findViewById(R.id.tvCurrentTemp);
        tabLayout = findViewById(R.id.tabLayout);
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }
}
