package com.android.josesantos.upcomingmovies.model.usecase;

import com.android.josesantos.upcomingmovies.data.entities.Movie;
import com.android.josesantos.upcomingmovies.model.repository.MoviesRepo;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by josesantos on 03/12/17.
 */

public class GetUpcommingMoviesList {

    @Inject
    MoviesRepo moviesRepo;

    @Inject
    GetUpcommingMoviesList() {
        //used by dagger
    }

    public List<Movie> execute(){
        return moviesRepo.getUpcommingMoviesList();
    }
}
