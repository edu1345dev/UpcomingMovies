package com.android.josesantos.upcomingmovies.injection;

import android.content.SharedPreferences;

import com.android.josesantos.upcomingmovies.data.local.DataStore;
import com.android.josesantos.upcomingmovies.data.local.DataStoreImpl;
import com.android.josesantos.upcomingmovies.model.datasource.CloudMoviesDataSource;
import com.android.josesantos.upcomingmovies.model.datasource.MoviesDataSource;
import com.android.josesantos.upcomingmovies.model.repository.MoviesRepo;
import com.android.josesantos.upcomingmovies.model.repository.MoviesRepoImpl;

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
    SharedPreferences sharedPreferences;

    @Provides
    @Singleton
    MoviesRepo provideUpcommingMoviesRepo(MoviesRepoImpl repo){
        return repo;
    }

    @Provides
    @Singleton
    DataStore providesDataStore(){
        return new DataStoreImpl(sharedPreferences);
    }

    @Provides
    @Singleton
    MoviesDataSource providesUpcommingMoviesDataSource(CloudMoviesDataSource dataSource){
        return dataSource;
    }
}

