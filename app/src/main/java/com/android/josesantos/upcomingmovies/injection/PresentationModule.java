package com.android.josesantos.upcomingmovies.injection;

import com.android.josesantos.upcomingmovies.model.usecase.GetGenres;
import com.android.josesantos.upcomingmovies.model.usecase.GetMovieDbConfiguration;
import com.android.josesantos.upcomingmovies.model.usecase.GetUpcommingMoviesList;
import com.android.josesantos.upcomingmovies.model.usecase.LoadUpcommingMovieList;
import com.android.josesantos.upcomingmovies.model.usecase.ReloadUpcommingMovieList;
import com.android.josesantos.upcomingmovies.presentation.UpcommingMoviesContract;
import com.android.josesantos.upcomingmovies.presentation.UpcommingMoviesPresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by josesantos on 03/12/17.
 */

@Module
public class PresentationModule {

    @Inject
    LoadUpcommingMovieList loadUpcommingMovieList;
    @Inject
    GetGenres getGenres;
    @Inject
    GetMovieDbConfiguration getMovieDbConfiguration;
    @Inject
    ReloadUpcommingMovieList reloadUpcommingMovieList;

    @Provides
    @Singleton
    UpcommingMoviesContract.Presenter provideUpcommingMoviesPresenter() {
        return new UpcommingMoviesPresenter(
                loadUpcommingMovieList,
                getGenres,
                getMovieDbConfiguration,
                reloadUpcommingMovieList);
    }
}
