package com.android.josesantos.upcomingmovies.data.local;

import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.data.entities.MovieDbConfiguration;

/**
 * Created by Jose Santos on 05/12/2017.
 */

public interface DataStore {
    void cacheGenres(Genres genres);
    void cacheMovieDbConfig(MovieDbConfiguration movieDbConfiguration);
    Genres getGenres();
    MovieDbConfiguration getMovieDbConfig();
}
