package com.android.josesantos.upcomingmovies.data.api.upcommingmovies;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.josesantos.upcomingmovies.data.api.ApiService;
import com.android.josesantos.upcomingmovies.data.api.constants.LanguageConstants;
import com.android.josesantos.upcomingmovies.data.entities.PageResponse;
import com.android.josesantos.upcomingmovies.data.entities.UpcommingMovie;

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

    public Observable<PageResponse<UpcommingMovie>> getUpcommingMoviesList(String page){
        return createService(UpcommingMoviesRoutes.class).getUpcommingMoviesList(page,LanguageConstants.EN_US);
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
