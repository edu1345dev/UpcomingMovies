package com.android.josesantos.upcomingmovies.data.api.upcommingmovies;

import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.data.entities.MovieDbConfiguration;
import com.android.josesantos.upcomingmovies.data.entities.PageResponse;
import com.android.josesantos.upcomingmovies.data.entities.UpcommingMovie;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by josesantos on 03/12/17.
 */

public interface UpcommingMoviesRoutes {

    @GET("movie/upcoming")
    Observable<PageResponse<UpcommingMovie>> getUpcommingMoviesList(@Query("page") String page,
                                                                    @Query("language") String language);

    @GET("genre/movie/list")
    Observable<Genres> getGenres(@Query("language") String language);

    @GET("configuration")
    Observable<MovieDbConfiguration> getMovieDbConfiguration();
}
