package com.android.josesantos.upcomingmovies.injection;

import android.content.Context;

import com.android.josesantos.upcomingmovies.data.api.ApiService;
import com.android.josesantos.upcomingmovies.data.api.movies.UpcommingMoviesService;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by josesantos on 03/12/17.
 */

@Module
public class ApiServiceModule {

    @Inject
    Context context;

    @Provides
    UpcommingMoviesService provideUpcommingMoviesService(){
        return new UpcommingMoviesService(context);
    }
}
