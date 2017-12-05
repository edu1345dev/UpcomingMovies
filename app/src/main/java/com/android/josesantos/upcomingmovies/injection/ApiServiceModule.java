package com.android.josesantos.upcomingmovies.injection;

import android.content.Context;

import com.android.josesantos.upcomingmovies.data.api.upcommingmovies.UpcommingMoviesService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by josesantos on 03/12/17.
 */

@Module
public class ApiServiceModule {

    @Provides
    UpcommingMoviesService provideUpcommingMoviesService(Context context){
        return new UpcommingMoviesService(context);
    }
}
