package com.android.josesantos.upcomingmovies.model.datasource;

import com.android.josesantos.upcomingmovies.data.entities.UpcommingMovie;

import java.util.List;

/**
 * Created by josesantos on 03/12/17.
 */

public interface UpcommingMoviesDataSource {
    void loadUpcommingMovies();
    List<UpcommingMovie> getUpcommingMoviesData();
}
