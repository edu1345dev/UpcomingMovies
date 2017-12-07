package com.android.josesantos.upcomingmovies.data.api.upcommingmovies;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.josesantos.upcomingmovies.data.api.ApiService;
import com.android.josesantos.upcomingmovies.data.api.constants.LanguageConstants;
import com.android.josesantos.upcomingmovies.data.api.exception.NetworkConnectionException;
import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.data.entities.Movie;
import com.android.josesantos.upcomingmovies.data.entities.MovieDbConfiguration;
import com.android.josesantos.upcomingmovies.data.entities.PageResponse;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by josesantos on 03/12/17.
 */

public class UpcommingMoviesService extends ApiService {

    private static final String TAG = "UpcommingMoviesService";

    private final Context context;

    @Inject
    public UpcommingMoviesService(Context context) {
        this.context = context;
    }

//    public Observable<PageResponse<Movie>> getMoviesList(String page){
//        return createService(UpcommingMoviesRoutes.class).getMoviesList(page, LanguageConstants.EN_US);
//    }

    public Observable<PageResponse<Movie>> getUpcommingMoviesList(String page){
        return createService(UpcommingMoviesRoutes.class)
                .getMoviesList(page,
                        LanguageConstants.EN_US,
                        "primary_release_date.asc",
                        getCurrentDate());
    }

    private String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
    }

    public Observable<Genres> getGenres(){
        Log.d(TAG, "getGenres: ");
        return Observable.create(e -> {
            if (isThereInternetConnection()){
                try {
                    e.onNext(createService(UpcommingMoviesRoutes.class)
                            .getGenres(LanguageConstants.EN_US).blockingFirst());
                    e.onComplete();
                }catch (Exception e1){
                    e.onError(new NetworkConnectionException(e1.getCause()));
                }
            }else {
                e.onError(new NetworkConnectionException());
            }
        });
    }

    public Observable<MovieDbConfiguration> getMovieDbConfiguration(){
        Log.d(TAG, "getMovieDbConfiguration: ");
        return Observable.create(e -> {
            if (isThereInternetConnection()){
                try {
                    e.onNext(createService(UpcommingMoviesRoutes.class)
                            .getMovieDbConfiguration().blockingFirst());
                    e.onComplete();
                }catch (Exception e1){
                    e.onError(new NetworkConnectionException(e1.getCause()));
                }
            }else {
                e.onError(new NetworkConnectionException());
            }
        });
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
