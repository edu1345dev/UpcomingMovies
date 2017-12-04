package com.android.josesantos.upcomingmovies;

import android.app.Application;

import com.android.josesantos.upcomingmovies.injection.ApiServiceModule;
import com.android.josesantos.upcomingmovies.injection.AppComponent;
import com.android.josesantos.upcomingmovies.injection.DaggerAppComponent;
import com.android.josesantos.upcomingmovies.injection.DataModule;
import com.android.josesantos.upcomingmovies.injection.PresentationModule;

/**
 * Created by josesantos on 03/12/17.
 */

public class AppApplication extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        initDagger();
    }

    private void initDagger() {
        appComponent = DaggerAppComponent
                .builder()
                .build();
    }

    public static AppComponent getAppComponent(){
        return appComponent;
    }
}
