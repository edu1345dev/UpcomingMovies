package com.android.josesantos.upcomingmovies.model.usecase;

import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.data.entities.MovieDbConfiguration;
import com.android.josesantos.upcomingmovies.model.repository.UpcommingMoviesRepoImpl;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by josesantos on 05/12/17.
 */

public class GetMovieDbConfiguration {
    @Inject
    public UpcommingMoviesRepoImpl upcommingMoviesRepo;

    @Inject
    public GetMovieDbConfiguration() {
    }

    public MovieDbConfiguration execute(){
        return upcommingMoviesRepo.getMovieDbConfiguration();
    }
}
