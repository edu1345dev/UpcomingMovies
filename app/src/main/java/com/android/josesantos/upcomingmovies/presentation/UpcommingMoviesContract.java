package com.android.josesantos.upcomingmovies.presentation;

import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.data.entities.MovieConfiguration;
import com.android.josesantos.upcomingmovies.data.entities.MovieListWrapper;
import com.android.josesantos.upcomingmovies.data.entities.Movie;

import java.util.List;

/**
 * Created by josesantos on 04/12/17.
 */

public class UpcommingMoviesContract {
    public interface View extends LoadView {
        void onMoviesLoaded(List<Movie> movieList);

        void onMoviesReload(List<Movie> movieList);

        void showInternetConnectionError();

        void showUnknownError();
    }

    public interface Presenter {
        void onResume(UpcommingMoviesContract.View view);

        void onPause();

        void onDestroy();

        void loadUpcommingMovies();

        void searchMovies(String query);

        void reloadUpcommingMovies();

        Genres getGenres();

        MovieConfiguration getMovieDbConfig();

        MovieListWrapper getMovieWrapper();
    }
}
