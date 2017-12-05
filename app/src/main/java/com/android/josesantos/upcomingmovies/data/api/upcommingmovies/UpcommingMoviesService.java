package com.android.josesantos.upcomingmovies.data.api.upcommingmovies;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.josesantos.upcomingmovies.data.api.ApiService;
import com.android.josesantos.upcomingmovies.data.api.constants.LanguageConstants;
import com.android.josesantos.upcomingmovies.data.api.exception.NetworkConnectionException;
import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.data.entities.MovieDbConfiguration;
import com.android.josesantos.upcomingmovies.data.entities.PageResponse;
import com.android.josesantos.upcomingmovies.data.entities.UpcommingMovie;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

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

    public Observable<PageResponse<UpcommingMovie>> getUpcommingMoviesList(String page){
        return Observable.create(e -> {
            if (isThereInternetConnection()){
                try {
                    e.onNext(createService(UpcommingMoviesRoutes.class)
                            .getUpcommingMoviesList(page,LanguageConstants.EN_US).blockingFirst());
                    e.onComplete();
                }catch (Exception e1){
                    e.onError(new NetworkConnectionException(e1.getCause()));
                }
            }else {
                e.onError(new NetworkConnectionException());
            }
        });
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
