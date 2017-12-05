package com.android.josesantos.upcomingmovies.model.repository;

import com.android.josesantos.upcomingmovies.data.entities.PageResponse;
import com.android.josesantos.upcomingmovies.data.entities.UpcommingMovie;
import com.android.josesantos.upcomingmovies.model.datasource.CloudUpcommingMovies;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by josesantos on 04/12/17.
 */

public class UpcommingMoviesRepoImpl implements UpcommingMoviesRepo {

    CloudUpcommingMovies cloudUpcommingMovies;

    @Inject
    public UpcommingMoviesRepoImpl(CloudUpcommingMovies cloudUpcommingMovies) {
        this.cloudUpcommingMovies = cloudUpcommingMovies;
    }

    @Override
    public Observable<PageResponse<UpcommingMovie>> loadUpcommingMovies() {
        return cloudUpcommingMovies.loadUpcommingMovies();
    }

    @Override
    public List<UpcommingMovie> getUpcommingMoviesList() {
        return cloudUpcommingMovies.getUpcommingMoviesData();
    }
}
