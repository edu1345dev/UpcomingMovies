package com.android.josesantos.upcomingmovies;

import android.app.Application;

import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.injection.ApiServiceModule;
import com.android.josesantos.upcomingmovies.injection.AppComponent;
import com.android.josesantos.upcomingmovies.injection.AppModule;
import com.android.josesantos.upcomingmovies.injection.DaggerAppComponent;
import com.android.josesantos.upcomingmovies.injection.DataModule;
import com.android.josesantos.upcomingmovies.injection.PresentationModule;
import com.android.josesantos.upcomingmovies.model.usecase.LoadGenres;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by josesantos on 03/12/17.
 */

public class AppApplication extends Application {

    private static AppComponent appComponent;
    @Inject
    LoadGenres loadGenres;

    @Override
    public void onCreate() {
        super.onCreate();

        initDagger();
        appComponent.inject(this);

        loadGenresAfterStart();
    }

    private void loadGenresAfterStart() {
        loadGenres.execute();
    }

    private void initDagger() {
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule())
                .apiServiceModule(new ApiServiceModule())
                .presentationModule(new PresentationModule())
                .build();
    }

    public static AppComponent getAppComponent(){
        return appComponent;
    }
}
