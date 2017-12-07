package com.android.josesantos.upcomingmovies.presentation.adapter;

import com.android.josesantos.upcomingmovies.data.entities.Movie;
import com.android.josesantos.upcomingmovies.data.entities.MovieWrapper;

/**
 * Created by josesantos on 07/12/17.
 */

public interface OnMovieClickListener {
    void onClick(MovieWrapper movieWrapper);
}
