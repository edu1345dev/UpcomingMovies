package com.android.josesantos.upcomingmovies.presentation;

import android.util.Log;

import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.data.entities.MovieConfiguration;
import com.android.josesantos.upcomingmovies.model.usecase.GetGenres;
import com.android.josesantos.upcomingmovies.model.usecase.GetMovieDbConfiguration;
import com.android.josesantos.upcomingmovies.model.usecase.LoadGenres;
import com.android.josesantos.upcomingmovies.model.usecase.LoadMovieDbConfiguration;

import java.net.ConnectException;
import java.net.UnknownHostException;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by josesantos on 08/12/17.
 */

public class SplashScreenPresenter implements SplashScreenContract.Presenter {
    private static final String TAG = "SplashScreenPresenter";

    private LoadMovieDbConfiguration loadMovieDbConfiguration;
    private LoadGenres loadGenres;
    private GetMovieDbConfiguration getMovieDbConfiguration;
    private GetGenres getGenres;
    private SplashScreenContract.View view;

    @Inject
    public SplashScreenPresenter(LoadMovieDbConfiguration loadMovieDbConfiguration, LoadGenres loadGenres, GetMovieDbConfiguration getMovieDbConfiguration, GetGenres getGenres) {
        this.loadMovieDbConfiguration = loadMovieDbConfiguration;
        this.loadGenres = loadGenres;
        this.getMovieDbConfiguration = getMovieDbConfiguration;
        this.getGenres = getGenres;
    }

    @Override
    public void onStart(SplashScreenContract.View view) {
        this.view = view;
    }

    @Override
    public void onStop() {
        view = null;
    }

    @Override
    public void onDestroy() {
        loadMovieDbConfiguration.dispose();
        loadGenres.dispose();
    }

    @Override
    public void loadFirstData() {
        if (isConfigInfoCached()) {
            if (hasViewAttached()){
                view.launchMainActivityDelayed();
            }
        } else {
            loadMovieDbConfig();
        }
    }

    private boolean isConfigInfoCached() {
        boolean isMovieDbConfigCached = getMovieDbConfiguration.execute() != null;
        boolean isGenresCached = getGenres.execute() != null;
        return isMovieDbConfigCached && isGenresCached;
    }

    @Override
    public void loadGenres() {
        loadGenres.execute(new DisposableObserver<Genres>() {
            @Override
            public void onNext(Genres value) {
                Log.d(TAG, "onNext: load genres");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ", e.getCause());
                handleOnError(e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: finished loading genres");
                handleOnComplete();
            }
        });
    }

    private void handleOnComplete() {
        if (hasViewAttached()){
            view.launchMainActivity();
        }
    }

    private void handleOnError(Throwable e) {
        if (e instanceof ConnectException || e instanceof UnknownHostException) {
            if (hasViewAttached()) {
                view.showInternetConnectionError();
            }
        } else {
            if (hasViewAttached()) {
                view.showUnknownError();
            }
        }
    }

    @Override
    public void loadMovieDbConfig() {
        loadMovieDbConfiguration.execute(new DisposableObserver<MovieConfiguration>() {
            @Override
            public void onNext(MovieConfiguration value) {
                Log.d(TAG, "onNext: load configuration file");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ", e.getCause());
                handleOnError(e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: finished loading configuration file");
                loadGenres();
            }
        });
    }

    private boolean hasViewAttached() {
        return view != null;
    }
}
