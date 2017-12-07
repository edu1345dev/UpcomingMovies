package com.android.josesantos.upcomingmovies.model.repository;

import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.data.entities.Movie;
import com.android.josesantos.upcomingmovies.data.entities.MovieDbConfiguration;
import com.android.josesantos.upcomingmovies.data.entities.PageResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by josesantos on 03/12/17.
 */

public interface UpcommingMoviesRepo {
    Observable<PageResponse<Movie>> loadUpcommingMovies();
    Observable<Genres> loadGenres();
    Genres getGenres();
    List<Movie> getUpcommingMoviesList();

    Observable<MovieDbConfiguration>  loadMovieDbConfiguration();

    MovieDbConfiguration getMovieDbConfiguration();

    Observable<PageResponse<Movie>> reloadUpcommingMovies();
}
