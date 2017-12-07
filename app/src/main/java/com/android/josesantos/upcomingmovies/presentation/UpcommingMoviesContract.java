package com.android.josesantos.upcomingmovies.presentation;

import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.data.entities.MovieDbConfiguration;
import com.android.josesantos.upcomingmovies.data.entities.MovieWrapper;
import com.android.josesantos.upcomingmovies.data.entities.Movie;

import java.util.List;

/**
 * Created by josesantos on 04/12/17.
 */

public class UpcommingMoviesContract {
    public interface View extends LoadView{
        void onMoviesLoaded(List<Movie> movieList);
    }

    public interface Presenter{
        void onResume(UpcommingMoviesContract.View view);

        void onPause();

        void onDestroy();

        void loadUpcommingMovies();

        void reloadUpcommingMovies();

        Genres getGenres();

        MovieDbConfiguration getMovieDbConfig();

        MovieWrapper getMovieWrapper();
    }
}
