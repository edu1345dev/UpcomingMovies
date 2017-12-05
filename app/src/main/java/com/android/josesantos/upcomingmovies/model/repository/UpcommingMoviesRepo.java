package com.android.josesantos.upcomingmovies.model.repository;

import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.data.entities.MovieDbConfiguration;
import com.android.josesantos.upcomingmovies.data.entities.PageResponse;
import com.android.josesantos.upcomingmovies.data.entities.UpcommingMovie;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by josesantos on 03/12/17.
 */

public interface UpcommingMoviesRepo {
    Observable<PageResponse<UpcommingMovie>> loadUpcommingMovies();
    Observable<Genres> loadGenres();
    List<Genres.Genre> getGenres();
    List<UpcommingMovie> getUpcommingMoviesList();

    Observable<MovieDbConfiguration>  loadMovieDbConfiguration();

    MovieDbConfiguration getMovieDbConfiguration();
}
