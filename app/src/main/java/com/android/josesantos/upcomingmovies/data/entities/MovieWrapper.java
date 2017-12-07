package com.android.josesantos.upcomingmovies.data.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by josesantos on 07/12/17.
 */

public class MovieWrapper {
    @SerializedName("movie")
    Movie movie;
    @SerializedName("genres")
    Genres genres;
    @SerializedName("movie_config")
    MovieConfiguration movieConfiguration;

    public MovieWrapper(Movie movie, Genres genres, MovieConfiguration movieConfiguration) {
        this.movie = movie;
        this.genres = genres;
        this.movieConfiguration = movieConfiguration;
    }

    public Movie getMovie() {
        return movie;
    }

    public Genres getGenres() {
        return genres;
    }

    public MovieConfiguration getMovieConfiguration() {
        return movieConfiguration;
    }
}
