package com.android.josesantos.upcomingmovies.presentation;

import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.data.entities.MovieDbConfiguration;
import com.android.josesantos.upcomingmovies.data.entities.UpcommingMovie;

import java.util.List;

/**
 * Created by josesantos on 04/12/17.
 */

public class UpcommingMoviesContract {
    public interface View{
        void onMoviesLoaded(List<UpcommingMovie> upcommingMovieList);
    }

    public interface Presenter{
        void onResume(UpcommingMoviesContract.View view);

        void onPause();

        void loadUpcommingMovies();

        List<Genres.Genre> getGenresList();

        MovieDbConfiguration getMovieDbConfig();
    }
}
