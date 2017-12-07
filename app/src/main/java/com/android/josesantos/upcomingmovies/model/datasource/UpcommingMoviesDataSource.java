package com.android.josesantos.upcomingmovies.model.datasource;

import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.data.entities.MovieDbConfiguration;
import com.android.josesantos.upcomingmovies.data.entities.PageResponse;
import com.android.josesantos.upcomingmovies.data.entities.Movie;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by josesantos on 03/12/17.
 */

public interface UpcommingMoviesDataSource {
    Observable<PageResponse<Movie>> loadUpcommingMovies();
    Observable<PageResponse<Movie>> reloadUpcommingMovies();
    Observable<Genres> loadGenres();
    Observable<MovieDbConfiguration> loadMovieDbConfigurations();
    Genres getGenres();
    MovieDbConfiguration getMovieDbConfiguration();
    List<Movie> getUpcommingMoviesData();
}
