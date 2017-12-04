package com.android.josesantos.upcomingmovies.model.usecase;

import com.android.josesantos.upcomingmovies.data.entities.UpcommingMovie;
import com.android.josesantos.upcomingmovies.model.repository.UpcommingMoviesRepo;
import com.android.josesantos.upcomingmovies.model.repository.UpcommingMoviesRepoImpl;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by josesantos on 04/12/17.
 */

public class LoadUpcommingMovieList {

    @Inject
    UpcommingMoviesRepoImpl upcommingMoviesRepo;

    public LoadUpcommingMovieList() {
    }

    public void execute(){
        upcommingMoviesRepo.loadUpcommingMovies();
    }
}
