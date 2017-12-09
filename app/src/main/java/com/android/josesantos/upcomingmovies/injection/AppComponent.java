package com.android.josesantos.upcomingmovies.injection;

import com.android.josesantos.upcomingmovies.AppApplication;
import com.android.josesantos.upcomingmovies.presentation.MainActivity;
import com.android.josesantos.upcomingmovies.presentation.SplashScreenActivity;
import com.android.josesantos.upcomingmovies.presentation.UpcommingMoviesFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by josesantos on 03/12/17.
 */
@Singleton
@Component(modules = {AppModule.class, PresentationModule.class, DataModule.class, ApiServiceModule.class})
public interface AppComponent {
    void inject(AppApplication touchCommandApplication);

    void inject(UpcommingMoviesFragment upcommingMoviesFragment);

    void inject(MainActivity mainActivity);

    void inject(SplashScreenActivity splashScreenActivity);
}
