package com.example.sabuj.tourmate;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sabuj.tourmate.fragments.HomeMenuFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FragmentTransaction transaction;
    HomeMenuFragment homeMenuFragment = new HomeMenuFragment();

    SharedPreferences sharedPreferences;
    final static String SHARED_NAME_STRING = "sharedp";
    static SharedPreferences.Editor preferenceEditor;



    @Override
    protected void onStart() {
        super.onStart();
        sharedPreferences = getSharedPreferences(MainActivity.SHARED_NAME_STRING, MODE_PRIVATE);
        boolean initialized = sharedPreferences.getBoolean("initialized", false);
        boolean isLoging = sharedPreferences.getBoolean("isLogin", false);

        if (!initialized || !isLoging) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("initialized", true);
            editor.apply();
            preferenceEditor = sharedPreferences.edit();
            preferenceEditor.apply();

            Intent intent=new Intent(MainActivity.this,LoginRegisterActivity.class);
            startActivity(intent);
            this.finish();



        } else {

        }


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getHomeFragment();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    public void getHomeFragment() {
        HomeMenuFragment homeMenuFragment = new HomeMenuFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.homeFrameLayout, homeMenuFragment);
        transaction.commit();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {

            sharedPreferences = getSharedPreferences(SHARED_NAME_STRING, MODE_PRIVATE);


            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isLogin", false);
            editor.apply();
            preferenceEditor = sharedPreferences.edit();
            preferenceEditor.apply();

            Intent i= new Intent(MainActivity.this,LoginRegisterActivity.class);
            startActivity(i);
            this.finish();

            return true;
        }else if (id == R.id.action_profile) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_moments) {
            transaction = getSupportFragmentManager().beginTransaction();
            homeMenuFragment.getMomentFragment(transaction);
        } else if (id == R.id.nav_nearby) {
            transaction = getSupportFragmentManager().beginTransaction();
            homeMenuFragment.getNearbyFragment(transaction);
        } else if (id == R.id.nav_expenses) {
            transaction = getSupportFragmentManager().beginTransaction();
            homeMenuFragment.getExpensesFragment(transaction);
        } else if (id == R.id.nav_events) {
            transaction = getSupportFragmentManager().beginTransaction();
            homeMenuFragment.getEventsFragment(transaction);
        } else if (id == R.id.nav_weather) {
            transaction = getSupportFragmentManager().beginTransaction();
            homeMenuFragment.getWeatherFragment(transaction);
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
