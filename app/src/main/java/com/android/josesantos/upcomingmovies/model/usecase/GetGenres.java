package com.android.josesantos.upcomingmovies.model.usecase;

import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.data.entities.UpcommingMovie;
import com.android.josesantos.upcomingmovies.model.repository.UpcommingMoviesRepo;
import com.android.josesantos.upcomingmovies.model.repository.UpcommingMoviesRepoImpl;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by josesantos on 05/12/17.
 */

public class GetGenres {
    @Inject
    public UpcommingMoviesRepoImpl upcommingMoviesRepo;

    @Inject
    public GetGenres() {
    }

    public List<Genres.Genre> execute(){
        return upcommingMoviesRepo.getGenres();
    }
}
