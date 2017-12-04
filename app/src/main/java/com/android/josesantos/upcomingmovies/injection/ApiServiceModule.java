package com.android.josesantos.upcomingmovies.injection;

import com.android.josesantos.upcomingmovies.data.api.ApiService;
import com.android.josesantos.upcomingmovies.data.api.movies.UpcommingMoviesService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by josesantos on 03/12/17.
 */

@Module
class ApiServiceModule {
    @Provides
    UpcommingMoviesService provideUpcommingMoviesService(UpcommingMoviesService service){
        return service;
    }
}
