package com.android.josesantos.upcomingmovies.data.api.movies;

import com.android.josesantos.upcomingmovies.data.entities.UpcommingMovie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by josesantos on 03/12/17.
 */

public interface UpcommingMoviesRoutes {

    @GET("movie/upcoming")
    Call<List<UpcommingMovie>> getUpcommingMoviesList(@Field("page") String page,
                                                      @Field("language") String language,
                                                      @Field("api_key") String portal);
}
