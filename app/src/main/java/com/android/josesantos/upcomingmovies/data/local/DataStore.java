package com.android.josesantos.upcomingmovies.data.local;

import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.data.entities.MovieConfiguration;

/**
 * Created by Jose Santos on 05/12/2017.
 */

public interface DataStore {
    void cacheGenres(Genres genres);
    void cacheMovieDbConfig(MovieConfiguration movieConfiguration);
    Genres getGenres();
    MovieConfiguration getMovieDbConfig();
}
