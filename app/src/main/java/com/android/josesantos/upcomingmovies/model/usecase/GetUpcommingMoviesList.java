package com.android.josesantos.upcomingmovies.model.usecase;

import com.android.josesantos.upcomingmovies.data.entities.UpcommingMovie;
import com.android.josesantos.upcomingmovies.model.repository.UpcommingMoviesRepo;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by josesantos on 03/12/17.
 */

public class GetUpcommingMoviesList {


    private final UpcommingMoviesRepo upcommingMoviesRepo;

    @Inject
    public GetUpcommingMoviesList(UpcommingMoviesRepo repo) {
        this.upcommingMoviesRepo = repo;
    }

    public List<UpcommingMovie> execute(){
        return upcommingMoviesRepo.getUpcommingMoviesList();
    }
}
