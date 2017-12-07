package com.android.josesantos.upcomingmovies.model.usecase;

import com.android.josesantos.upcomingmovies.data.entities.MovieConfiguration;
import com.android.josesantos.upcomingmovies.model.repository.MoviesRepoImpl;

import javax.inject.Inject;

/**
 * Created by josesantos on 05/12/17.
 */

public class GetMovieDbConfiguration {
    @Inject
    public MoviesRepoImpl upcommingMoviesRepo;

    @Inject
    public GetMovieDbConfiguration() {
    }

    public MovieConfiguration execute(){
        return upcommingMoviesRepo.getMovieDbConfiguration();
    }
}
