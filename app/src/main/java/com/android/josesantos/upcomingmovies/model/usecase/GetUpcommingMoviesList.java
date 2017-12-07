package com.android.josesantos.upcomingmovies.model.usecase;

import com.android.josesantos.upcomingmovies.data.entities.Movie;
import com.android.josesantos.upcomingmovies.model.repository.UpcommingMoviesRepo;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by josesantos on 03/12/17.
 */

public class GetUpcommingMoviesList {

    @Inject
    public UpcommingMoviesRepo upcommingMoviesRepo;

    @Inject
    public GetUpcommingMoviesList() {
    }

    public List<Movie> execute(){
        return upcommingMoviesRepo.getUpcommingMoviesList();
    }
}
