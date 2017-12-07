package com.android.josesantos.upcomingmovies.data.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by josesantos on 06/12/17.
 */

public class MovieWrapper {

    List<Movie> movieList = new ArrayList<>();
    Genres genres;
    MovieDbConfiguration movieDbConfiguration;

    public MovieWrapper(List<Movie> movieList, Genres genres, MovieDbConfiguration movieDbConfiguration) {
        this.movieList = movieList;
        this.genres = genres;
        this.movieDbConfiguration = movieDbConfiguration;
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

    public MovieDbConfiguration getMovieDbConfiguration() {
        return movieDbConfiguration;
    }

    public void setMovieDbConfiguration(MovieDbConfiguration movieDbConfiguration) {
        this.movieDbConfiguration = movieDbConfiguration;
    }
}
