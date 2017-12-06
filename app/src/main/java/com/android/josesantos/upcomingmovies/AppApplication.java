package com.android.josesantos.upcomingmovies;

import android.app.Application;
import android.util.Log;

import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.data.entities.MovieDbConfiguration;
import com.android.josesantos.upcomingmovies.injection.ApiServiceModule;
import com.android.josesantos.upcomingmovies.injection.AppComponent;
import com.android.josesantos.upcomingmovies.injection.AppModule;
import com.android.josesantos.upcomingmovies.injection.DaggerAppComponent;
import com.android.josesantos.upcomingmovies.injection.DataModule;
import com.android.josesantos.upcomingmovies.injection.PresentationModule;
import com.android.josesantos.upcomingmovies.model.usecase.LoadGenres;
import com.android.josesantos.upcomingmovies.model.usecase.LoadMovieDbConfiguration;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by josesantos on 03/12/17.
 */

public class AppApplication extends Application {

    private static final String TAG = "AppApplication";
    private static AppComponent appComponent;
    @Inject
    LoadGenres loadGenres;
    @Inject
    LoadMovieDbConfiguration loadMovieDbConfiguration;

    @Override
    public void onCreate() {
        super.onCreate();

        initDagger();
        appComponent.inject(this);

        loadGenresAfterStart();
        loadMovieDbConfigAfterStart();
    }

    private void loadMovieDbConfigAfterStart() {
        loadMovieDbConfiguration.execute(new DisposableObserver<MovieDbConfiguration>() {
            @Override
            public void onNext(MovieDbConfiguration value) {
                Log.d(TAG, "onNext: load configuration file");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ", e.getCause());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: finished loading configuration file");
            }
        });
    }

    private void loadGenresAfterStart() {
        loadGenres.execute(new DisposableObserver<Genres>() {
            @Override
            public void onNext(Genres value) {
                Log.d(TAG, "onNext: load genres");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ", e.getCause());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: finished loading genres");
            }
        });
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
