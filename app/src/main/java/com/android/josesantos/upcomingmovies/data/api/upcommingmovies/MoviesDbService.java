package com.android.josesantos.upcomingmovies.data.api.upcommingmovies;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

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

    private static final String TAG = "MoviesDbService";

    private final Context context;

    @Inject
    public MoviesDbService(Context context) {
        this.context = context;
    }

    public Observable<PageResponse<Movie>> getUpcommingMoviesList(String page){
        return getService().getMoviesList(page,
                        LanguageConstants.EN_US,
                        "primary_release_date.asc",
                        getCurrentDate());
    }

    private MoviesDbRoutes getService() {
        return createService(MoviesDbRoutes.class);
    }

    public Observable<Genres> getGenres(){
        Log.d(TAG, "getGenres: ");
        return getService().getGenres(LanguageConstants.EN_US);
    }

    public Observable<MovieConfiguration> getMovieDbConfiguration(){
        Log.d(TAG, "getMovieConfiguration: ");
        return getService().getMovieDbConfiguration();
    }

    private String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
    }

    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }
}
