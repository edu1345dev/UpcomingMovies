package com.android.josesantos.upcomingmovies.injection;

import com.android.josesantos.upcomingmovies.presentation.SplashScreenPresenter;
import com.android.josesantos.upcomingmovies.presentation.UpcommingMoviesContract;
import com.android.josesantos.upcomingmovies.presentation.UpcommingMoviesPresenter;
import com.android.josesantos.upcomingmovies.presentation.SplashScreenContract;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by josesantos on 03/12/17.
 */

@Module
public class PresentationModule {

    @Provides
    @Singleton
    UpcommingMoviesContract.Presenter provideUpcommingMoviesPresenter(UpcommingMoviesPresenter presenter) {
     return presenter;
    }

    @Provides
    @Singleton
    SplashScreenContract.Presenter provideSplashScreebPresenter(SplashScreenPresenter presenter) {
        return presenter;
    }
}
