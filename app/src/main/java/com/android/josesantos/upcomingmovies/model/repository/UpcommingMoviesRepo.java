package com.android.josesantos.upcomingmovies.model.repository;

import com.android.josesantos.upcomingmovies.data.entities.UpcommingMovie;

import java.util.List;

/**
 * Created by josesantos on 03/12/17.
 */

public interface UpcommingMoviesRepo {
    void loadUpcommingMovies();
    List<UpcommingMovie> getUpcommingMoviesList();
}
