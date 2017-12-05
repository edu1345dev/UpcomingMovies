package com.android.josesantos.upcomingmovies.model.datasource;

import com.android.josesantos.upcomingmovies.data.entities.PageResponse;
import com.android.josesantos.upcomingmovies.data.entities.UpcommingMovie;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by josesantos on 03/12/17.
 */

public interface UpcommingMoviesDataSource {
    Observable<PageResponse<UpcommingMovie>> loadUpcommingMovies();
    List<UpcommingMovie> getUpcommingMoviesData();
}
