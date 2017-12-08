package com.android.josesantos.upcomingmovies.model.repository;

import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.data.entities.Movie;
import com.android.josesantos.upcomingmovies.data.entities.MovieConfiguration;
import com.android.josesantos.upcomingmovies.data.entities.PageResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by josesantos on 03/12/17.
 */

public interface MoviesRepo {
    Observable<PageResponse<Movie>> loadUpcommingMovies();
    Observable<PageResponse<Movie>> loadSearchMovies(String query);
    Observable<Genres> loadGenres();
    Genres getGenres();
    List<Movie> getUpcommingMoviesList();

    Observable<MovieConfiguration>  loadMovieDbConfiguration();

    MovieConfiguration getMovieDbConfiguration();

    Observable<PageResponse<Movie>> reloadUpcommingMovies();
}
