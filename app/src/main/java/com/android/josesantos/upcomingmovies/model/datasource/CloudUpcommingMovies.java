package com.android.josesantos.upcomingmovies.model.datasource;

import android.util.Log;

import com.android.josesantos.upcomingmovies.data.api.RequestHandler;
import com.android.josesantos.upcomingmovies.data.api.constants.ApiConstants;
import com.android.josesantos.upcomingmovies.data.api.movies.UpcommingMoviesService;
import com.android.josesantos.upcomingmovies.data.entities.PageResponse;
import com.android.josesantos.upcomingmovies.data.entities.UpcommingMovie;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by josesantos on 03/12/17.
 */

public class CloudUpcommingMovies implements UpcommingMoviesDataSource {
    private static final String TAG = "CloudUpcommingMovies";

    private PageResponse<UpcommingMovie> pageResponse = new PageResponse<>();

    @Inject
    UpcommingMoviesService moviesService;

    @Inject
    public CloudUpcommingMovies() {
    }

    @Override
    public void loadUpcommingMovies() {
        moviesService.getUpcommingMoviesList(pageResponse.getPage().toString(),
                new RequestHandler<PageResponse<UpcommingMovie>>() {
            @Override
            public void onSucess(PageResponse<UpcommingMovie> response) {
                setPageResponse(response);
            }

            @Override
            public void onError(String message) {

            }

            @Override
            public void onFailure(String message) {

            }
        });
    }

    private void setPageResponse(PageResponse<UpcommingMovie> response) {
        pageResponse.setPage(response.getPage()+1);
        pageResponse.getResults().addAll(response.getResults());
        pageResponse.setTotalPages(response.getTotalPages());
        pageResponse.setTotalResults(response.getTotalResults());
    }

    @Override
    public List<UpcommingMovie> getUpcommingMoviesData() {
        return moviesService.getUpcommingMovieList();
    }
}
