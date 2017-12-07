package com.android.josesantos.upcomingmovies.model.repository;

import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.data.entities.Movie;
import com.android.josesantos.upcomingmovies.data.entities.MovieDbConfiguration;
import com.android.josesantos.upcomingmovies.data.entities.PageResponse;
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
    public Observable<PageResponse<Movie>> loadUpcommingMovies() {
        return cloudUpcommingMovies.loadUpcommingMovies();
    }

    @Override
    public Observable<Genres> loadGenres() {
        return cloudUpcommingMovies.loadGenres();
    }

    @Override
    public Genres getGenres() {
        return cloudUpcommingMovies.getGenres();
    }

    @Override
    public List<Movie> getUpcommingMoviesList() {
        return cloudUpcommingMovies.getUpcommingMoviesData();
    }

    @Override
    public Observable<PageResponse<Movie>> reloadUpcommingMovies() {
        return cloudUpcommingMovies.reloadUpcommingMovies();
    }

    @Override
    public MovieDbConfiguration getMovieDbConfiguration() {
        return cloudUpcommingMovies.getMovieDbConfiguration();
    }

    @Override
    public Observable<MovieDbConfiguration>  loadMovieDbConfiguration() {
        return cloudUpcommingMovies.loadMovieDbConfigurations();
    }
}
