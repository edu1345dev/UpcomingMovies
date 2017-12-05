package com.android.josesantos.upcomingmovies.injection;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.android.josesantos.upcomingmovies.data.local.DataStore;
import com.android.josesantos.upcomingmovies.data.local.DataStoreImpl;
import com.android.josesantos.upcomingmovies.model.datasource.CloudUpcommingMovies;
import com.android.josesantos.upcomingmovies.model.repository.UpcommingMoviesRepo;
import com.android.josesantos.upcomingmovies.model.repository.UpcommingMoviesRepoImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by josesantos on 03/12/17.
 */
@Module
public class DataModule {

    @Inject
    CloudUpcommingMovies cloudUpcommingMovies;
    @Inject
    SharedPreferences sharedPreferences;

    @Provides
    UpcommingMoviesRepo provideUpcommingMoviesRepo(){
        return new UpcommingMoviesRepoImpl(cloudUpcommingMovies);
    }

    @Provides
    DataStore providesDataStore(){
        return new DataStoreImpl(sharedPreferences);
    }
}
