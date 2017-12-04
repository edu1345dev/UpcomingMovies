package com.android.josesantos.upcomingmovies.injection;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jose Santos on 04/12/2017.
 */

@Module
public class AppModule {

    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    Context providesApplication(){
        return application;
    }

}
