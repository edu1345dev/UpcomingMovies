package com.android.josesantos.upcomingmovies.model.datasource;

import android.content.SharedPreferences;

import com.android.josesantos.upcomingmovies.data.api.RequestHandler;
import com.android.josesantos.upcomingmovies.data.api.upcommingmovies.UpcommingMoviesService;
import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.data.entities.PageResponse;
import com.android.josesantos.upcomingmovies.data.entities.UpcommingMovie;
import com.google.gson.Gson;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by josesantos on 03/12/17.
 */

public class CloudUpcommingMovies implements UpcommingMoviesDataSource {
    private static final String TAG = "CloudUpcommingMovies";

    private static String GENRES = "genres";
    private PageResponse<UpcommingMovie> pageResponse = new PageResponse<>();

    @Inject
    UpcommingMoviesService moviesService;
    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    public CloudUpcommingMovies() {
    }

    @Override
    public Observable<PageResponse<UpcommingMovie>> loadUpcommingMovies() {
        return moviesService.getUpcommingMoviesList(pageResponse.getPage().toString())
                .doOnNext(this::setPageResponse);

    }

    @Override
    public Observable<Genres> loadGenres() {
        return moviesService.getGenres()
                .doOnNext(this::cacheGenres);
    }

    @Override
    public List<Genres.Genre> getGenres() {
        Gson gson = new Gson();
        String genres = sharedPreferences.getString(GENRES,"");
        return gson.fromJson(genres, Genres.class).getGenres();
    }

    private void cacheGenres(Genres genres) {
        Gson gson = new Gson();
        String genresJson = gson.toJson(genres);

        sharedPreferences.edit().putString(GENRES,genresJson).apply();
    }

    private void setPageResponse(PageResponse<UpcommingMovie> response) {
        pageResponse.setPage(response.getPage()+1);
        pageResponse.getResults().addAll(response.getResults());
        pageResponse.setTotalPages(response.getTotalPages());
        pageResponse.setTotalResults(response.getTotalResults());
    }

    @Override
    public List<UpcommingMovie> getUpcommingMoviesData() {
        return pageResponse.getResults();
    }
}
