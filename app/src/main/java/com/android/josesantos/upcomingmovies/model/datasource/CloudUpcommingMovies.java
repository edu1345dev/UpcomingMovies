package com.android.josesantos.upcomingmovies.model.datasource;

import android.util.Log;

import com.android.josesantos.upcomingmovies.data.api.upcommingmovies.UpcommingMoviesService;
import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.data.entities.MovieDbConfiguration;
import com.android.josesantos.upcomingmovies.data.entities.PageResponse;
import com.android.josesantos.upcomingmovies.data.entities.UpcommingMovie;
import com.android.josesantos.upcomingmovies.data.local.DataStoreImpl;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by josesantos on 03/12/17.
 */

public class CloudUpcommingMovies implements UpcommingMoviesDataSource {
    private static final String TAG = "CloudUpcommingMovies";

    private PageResponse<UpcommingMovie> pageResponse = new PageResponse<>();

    @Inject
    UpcommingMoviesService moviesService;
    @Inject
    DataStoreImpl dataStore;


    @Inject
    public CloudUpcommingMovies() {
    }

    @Override
    public Observable<PageResponse<UpcommingMovie>> loadUpcommingMovies() {
        return moviesService.getUpcommingMoviesList(pageResponse.getPage().toString())
                .doOnNext(this::setPageResponse);

    }

    @Override
    public Observable<MovieDbConfiguration> loadMovieDbConfigurations() {
        return moviesService.getMovieDbConfiguration()
                .doOnNext(this::cacheMovieDbConfig);
    }

    private void cacheMovieDbConfig(MovieDbConfiguration movieDbConfiguration) {
        dataStore.cacheMovieDbConfig(movieDbConfiguration);
    }

    @Override
    public MovieDbConfiguration getMovieDbConfiguration() {
        return dataStore.getMovieDbConfig();
    }

    @Override
    public Observable<Genres> loadGenres() {
        return moviesService.getGenres()
                .doOnNext(this::cacheGenres);
    }

    @Override
    public List<Genres.Genre> getGenres() {
        return dataStore.getGenres().getGenresList();
    }

    private void cacheGenres(Genres genres) {
        dataStore.cacheGenres(genres);
    }

    private void setPageResponse(PageResponse<UpcommingMovie> response) {
        pageResponse.setPage(response.getPage() + 1);
        pageResponse.getResults().addAll(response.getResults());
        pageResponse.setTotalPages(response.getTotalPages());
        pageResponse.setTotalResults(response.getTotalResults());
    }

    @Override
    public List<UpcommingMovie> getUpcommingMoviesData() {
        return pageResponse.getResults();
    }
}
