package com.android.josesantos.upcomingmovies.injection;

import com.android.josesantos.upcomingmovies.AppApplication;
import com.android.josesantos.upcomingmovies.presentation.UpcommingMoviesActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by josesantos on 03/12/17.
 */
@Singleton
@Component(modules = {AppModule.class, PresentationModule.class, DataModule.class, ApiServiceModule.class})
public interface AppComponent {
    void inject(AppApplication touchCommandApplication);

    void inject(UpcommingMoviesActivity upcommingMoviesActivity);
}
