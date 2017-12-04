package com.android.josesantos.upcomingmovies.injection;

import android.app.Application;
import android.content.Context;

import com.android.josesantos.upcomingmovies.model.datasource.CloudUpcommingMovies;
import com.android.josesantos.upcomingmovies.model.repository.UpcommingMoviesRepo;
import com.android.josesantos.upcomingmovies.model.repository.UpcommingMoviesRepoImpl;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by josesantos on 03/12/17.
 */
@Module
public class DataModule {

    @Inject
    CloudUpcommingMovies cloudUpcommingMovies;

    @Provides
    UpcommingMoviesRepo provideUpcommingMoviesRepo(){
        return new UpcommingMoviesRepoImpl(cloudUpcommingMovies);
    }
}
