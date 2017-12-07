package com.android.josesantos.upcomingmovies.presentation;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.josesantos.upcomingmovies.AppApplication;
import com.android.josesantos.upcomingmovies.R;
import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.data.entities.MovieConfiguration;
import com.android.josesantos.upcomingmovies.model.usecase.GetGenres;
import com.android.josesantos.upcomingmovies.model.usecase.GetMovieDbConfiguration;
import com.android.josesantos.upcomingmovies.model.usecase.LoadGenres;
import com.android.josesantos.upcomingmovies.model.usecase.LoadMovieDbConfiguration;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashScreenActivity extends BaseActivity {
    private static final String TAG = "SplashScreenActivity";

    @Inject
    LoadMovieDbConfiguration loadMovieDbConfiguration;
    @Inject
    LoadGenres loadGenres;
    @Inject
    GetMovieDbConfiguration getMovieDbConfiguration;
    @Inject
    GetGenres getGenres;

    private boolean isMovieDbConfigCached = false;
    private boolean isGenresCached = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        AppApplication.getAppComponent().inject(this);

        loadFirstData();
    }

    private void loadFirstData() {
        if (isConfigInfoCached()){
            launchMainActivity();
        }else if (isThereInternetConnection()) {
            loadMovieDbConfig();
        } else {
            showConnectionError();
        }
    }

    private boolean isConfigInfoCached() {
        isMovieDbConfigCached = getMovieDbConfiguration.execute() != null;
        isGenresCached = getGenres.execute() != null;
        return isMovieDbConfigCached && isGenresCached;
    }

    private void loadMovieDbConfig() {
        loadMovieDbConfiguration.execute(new DisposableObserver<MovieConfiguration>() {
            @Override
            public void onNext(MovieConfiguration value) {
                Log.d(TAG, "onNext: load configuration file");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ", e.getCause());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: finished loading configuration file");
                loadGenres();
            }
        });
    }

    private void loadGenres() {
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
                launchMainActivity();
            }
        });
    }

    private void launchMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    void onRetryConnection() {
        loadFirstData();
    }
}
