package com.android.josesantos.upcomingmovies.data.api.movies;

import com.android.josesantos.upcomingmovies.data.entities.PageResponse;
import com.android.josesantos.upcomingmovies.data.entities.UpcommingMovie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by josesantos on 03/12/17.
 */

public interface UpcommingMoviesRoutes {

    @GET("movie/upcoming")
    Call<PageResponse<UpcommingMovie>> getUpcommingMoviesList(@Query("page") String page,
                                              @Query("language") String language,
                                              @Query("api_key") String portal);
}
