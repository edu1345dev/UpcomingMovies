package com.android.josesantos.upcomingmovies.model.repository;

import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.data.entities.Movie;
import com.android.josesantos.upcomingmovies.data.entities.MovieConfiguration;
import com.android.josesantos.upcomingmovies.data.entities.PageResponse;
import com.android.josesantos.upcomingmovies.model.datasource.MoviesDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by josesantos on 04/12/17.
 */

public class MoviesRepoImpl implements MoviesRepo {


    MoviesDataSource cloudUpcommingMovies;

    @Inject
    public MoviesRepoImpl(MoviesDataSource cloudUpcommingMovies) {
        this.cloudUpcommingMovies = cloudUpcommingMovies;
    }

    @Override
    public Observable<PageResponse<Movie>> loadUpcommingMovies() {
        return cloudUpcommingMovies.loadUpcommingMovies();
    }

    @Override
    public Observable<PageResponse<Movie>> loadSearchMovies(String query) {
        return cloudUpcommingMovies.loadSearchMovies(query);
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
    public MovieConfiguration getMovieDbConfiguration() {
        return cloudUpcommingMovies.getMovieDbConfiguration();
    }

    @Override
    public Observable<MovieConfiguration>  loadMovieDbConfiguration() {
        return cloudUpcommingMovies.loadMovieDbConfigurations();
    }
}
