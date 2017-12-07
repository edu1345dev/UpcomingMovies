package com.android.josesantos.upcomingmovies.data.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by josesantos on 06/12/17.
 */

public class MovieListWrapper {

    List<Movie> movieList = new ArrayList<>();
    Genres genres;
    MovieConfiguration movieConfiguration;

    public MovieListWrapper(List<Movie> movieList, Genres genres, MovieConfiguration movieConfiguration) {
        this.movieList = movieList;
        this.genres = genres;
        this.movieConfiguration = movieConfiguration;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public Genres getGenres() {
        return genres;
    }

    public void setGenres(Genres genres) {
        this.genres = genres;
    }

    public MovieConfiguration getMovieConfiguration() {
        return movieConfiguration;
    }

    public void setMovieConfiguration(MovieConfiguration movieConfiguration) {
        this.movieConfiguration = movieConfiguration;
    }
}
