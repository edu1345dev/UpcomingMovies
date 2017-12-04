package com.android.josesantos.upcomingmovies.model.datasource;

import com.android.josesantos.upcomingmovies.data.api.RequestHandler;
import com.android.josesantos.upcomingmovies.data.api.movies.UpcommingMoviesService;
import com.android.josesantos.upcomingmovies.data.entities.UpcommingMovie;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by josesantos on 03/12/17.
 */

public class CloudUpcommingMovies implements UpcommingMoviesDataSource {
    private String page = "1";

    @Inject
    UpcommingMoviesService moviesService;

    @Override
    public List<UpcommingMovie> getUpcommingMoviesData() {
        moviesService.getUpcommingMoviesList(page, new RequestHandler<List<UpcommingMovie>>() {
            @Override
            public void onSucess(List<UpcommingMovie> response) {

            }

            @Override
            public void onError(String message) {

            }

            @Override
            public void onFailure(String message) {

            }
        });
        return null;
    }
}
