package com.android.josesantos.upcomingmovies.data.local;

import android.content.SharedPreferences;

import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.data.entities.MovieConfiguration;
import com.google.gson.Gson;

import javax.inject.Inject;

/**
 * Created by Jose Santos on 05/12/2017.
 */

public class DataStoreImpl implements DataStore {

    private static String GENRES = "genres";
    private static String MOVIE_DB_CONFIG = "movie_db_config";
    SharedPreferences sharedPreferences;

    @Inject
    public DataStoreImpl(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public void cacheGenres(Genres genres) {
        Gson gson = new Gson();
        String genresJson = gson.toJson(genres);
        sharedPreferences.edit().putString(GENRES,genresJson).apply();
    }

    @Override
    public void cacheMovieDbConfig(MovieConfiguration movieConfiguration) {
        Gson gson = new Gson();
        String movieDbConfig = gson.toJson(movieConfiguration);
        sharedPreferences.edit().putString(MOVIE_DB_CONFIG,movieDbConfig).apply();
    }

    @Override
    public Genres getGenres() {
        Gson gson = new Gson();
        String genres = sharedPreferences.getString(GENRES,"");
        return gson.fromJson(genres, Genres.class);
    }

    @Override
    public MovieConfiguration getMovieDbConfig() {
        Gson gson = new Gson();
        String movieDbConfig = sharedPreferences.getString(MOVIE_DB_CONFIG,"");
        return gson.fromJson(movieDbConfig, MovieConfiguration.class);
    }
}
