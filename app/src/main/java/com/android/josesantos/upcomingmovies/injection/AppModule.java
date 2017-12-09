package com.android.josesantos.upcomingmovies.injection;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jose Santos on 04/12/2017.
 */

@Module
public class AppModule {

    private static String APP_SHARED_PREFS = "app_shared_prefs";
    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    Context providesApplication(){
        return application;
    }

    @Provides
    SharedPreferences providesSharedPreferences(){
        return application.getApplicationContext().getSharedPreferences(APP_SHARED_PREFS, Context.MODE_PRIVATE);
    }
}
