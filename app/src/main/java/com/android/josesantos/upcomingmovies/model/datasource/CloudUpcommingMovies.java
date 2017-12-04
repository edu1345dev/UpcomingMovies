package com.android.josesantos.upcomingmovies.model.datasource;

import android.util.Log;

import com.android.josesantos.upcomingmovies.data.api.RequestHandler;
import com.android.josesantos.upcomingmovies.data.api.constants.ApiConstants;
import com.android.josesantos.upcomingmovies.data.api.movies.UpcommingMoviesService;
import com.android.josesantos.upcomingmovies.data.entities.UpcommingMovie;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by josesantos on 03/12/17.
 */

public class CloudUpcommingMovies implements UpcommingMoviesDataSource {
    private static final String TAG = "CloudUpcommingMovies";
    private String page = "1";

    @Inject
    UpcommingMoviesService moviesService;

    @Inject
    public CloudUpcommingMovies() {
    }

    @Override
    public void loadUpcommingMovies() {
        moviesService.getUpcommingMoviesList(page, new RequestHandler<List<UpcommingMovie>>() {
            @Override
            public void onSucess(List<UpcommingMovie> response) {
                Log.d(TAG, "onSucess: "+response.size());
            }

            @Override
            public void onError(String message) {
                Log.d(TAG, "onError: "+message);
            }

            @Override
            public void onFailure(String message) {
                if (message.equals(ApiConstants.NO_INTERNET_CONNECTION)){
                    Log.d(TAG, "onFailure: "+ApiConstants.NO_INTERNET_CONNECTION);
                }
            }
        });
    }

    @Override
    public List<UpcommingMovie> getUpcommingMoviesData() {
        return moviesService.getUpcommingMovieList();
    }
}
