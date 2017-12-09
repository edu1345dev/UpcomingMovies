package com.android.josesantos.upcomingmovies.data.api.upcommingmovies;

import com.android.josesantos.upcomingmovies.data.api.ApiService;
import com.android.josesantos.upcomingmovies.data.api.constants.LanguageConstants;
import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.data.entities.Movie;
import com.android.josesantos.upcomingmovies.data.entities.MovieConfiguration;
import com.android.josesantos.upcomingmovies.data.entities.PageResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by josesantos on 03/12/17.
 */

public class MoviesDbService extends ApiService {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String SORT_BY = "primary_release_date.asc";

    @Inject
    public MoviesDbService() {
        //used by dagger
    }

    public Observable<PageResponse<Movie>> getUpcommingMoviesList(String page){
        return getService().getMoviesList(page,
                        LanguageConstants.EN_US,
                        SORT_BY,
                        getCurrentDate());
    }

    public Observable<PageResponse<Movie>> searchMovie(String page, String query){
        return getService().getSearchMoviesList(page,
                query,
                LanguageConstants.EN_US);
    }

    private MoviesDbRoutes getService() {
        return createService(MoviesDbRoutes.class);
    }

    public Observable<Genres> getGenres(){
        return getService().getGenres(LanguageConstants.EN_US);
    }

    public Observable<MovieConfiguration> getMovieDbConfiguration(){
        return getService().getMovieDbConfiguration();
    }

    private String getCurrentDate() {
        return new SimpleDateFormat(DATE_FORMAT).format(Calendar.getInstance().getTime());
    }
}
