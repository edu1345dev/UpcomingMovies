package com.android.josesantos.upcomingmovies.model.datasource;

import com.android.josesantos.upcomingmovies.data.api.upcommingmovies.MoviesDbService;
import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.data.entities.MovieConfiguration;
import com.android.josesantos.upcomingmovies.data.entities.PageResponse;
import com.android.josesantos.upcomingmovies.data.entities.Movie;
import com.android.josesantos.upcomingmovies.data.local.DataStoreImpl;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by josesantos on 03/12/17.
 */

public class CloudMoviesDataSource implements MoviesDataSource {
    private static final String TAG = "CloudMoviesDataSource";

    private PageResponse<Movie> pageResponse = new PageResponse<>();

    @Inject
    MoviesDbService moviesService;
    @Inject
    DataStoreImpl dataStore;


    @Inject
    public CloudMoviesDataSource() {
    }

    @Override
    public Observable<PageResponse<Movie>> loadUpcommingMovies() {
        return moviesService.getUpcommingMoviesList(pageResponse.getPage().toString())
                .doOnNext(this::setPageResponse);

    }

    @Override
    public Observable<PageResponse<Movie>> reloadUpcommingMovies() {
        pageResponse.setPage(1);
        return moviesService.getUpcommingMoviesList(pageResponse.getPage().toString())
                .doOnNext(this::setPageResponse);
    }

    @Override
    public Observable<MovieConfiguration> loadMovieDbConfigurations() {
        return moviesService.getMovieDbConfiguration()
                .doOnNext(this::cacheMovieDbConfig);
    }

    private void cacheMovieDbConfig(MovieConfiguration movieConfiguration) {
        dataStore.cacheMovieDbConfig(movieConfiguration);
    }

    @Override
    public MovieConfiguration getMovieDbConfiguration() {
        return dataStore.getMovieDbConfig();
    }

    @Override
    public Observable<Genres> loadGenres() {
        return moviesService.getGenres()
                .doOnNext(this::cacheGenres);
    }

    @Override
    public Genres getGenres() {
        return dataStore.getGenres();
    }

    private void cacheGenres(Genres genres) {
        dataStore.cacheGenres(genres);
    }

    private void setPageResponse(PageResponse<Movie> response) {
        pageResponse.setPage(response.getPage() + 1);
        pageResponse.getResults().addAll(response.getResults());
        pageResponse.setTotalPages(response.getTotalPages());
        pageResponse.setTotalResults(response.getTotalResults());
    }

    @Override
    public List<Movie> getUpcommingMoviesData() {
        return pageResponse.getResults();
    }
}
