package com.android.josesantos.upcomingmovies.data.api.movies;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.josesantos.upcomingmovies.data.api.constants.ApiConstants;
import com.android.josesantos.upcomingmovies.data.api.ApiService;
import com.android.josesantos.upcomingmovies.data.api.constants.LanguageConstants;
import com.android.josesantos.upcomingmovies.data.api.RequestHandler;
import com.android.josesantos.upcomingmovies.data.entities.PageResponse;
import com.android.josesantos.upcomingmovies.data.entities.UpcommingMovie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by josesantos on 03/12/17.
 */

public class UpcommingMoviesService extends ApiService {

    private static final String TAG = "UpcommingMoviesService";

    private final Context context;
    private List<UpcommingMovie> upcommingMovieList = new ArrayList<>();

    @Inject
    public UpcommingMoviesService(Context context) {
        this.context = context;
    }

    public void getUpcommingMoviesList(String page, final RequestHandler<PageResponse<UpcommingMovie>> requestHandler){
        if (isThereInternetConnection()){
            createService(UpcommingMoviesRoutes.class)
                    .getUpcommingMoviesList(page, LanguageConstants.EN_US, ApiConstants.API_KEY)
                    .enqueue(new Callback<PageResponse<UpcommingMovie>>() {
                        @Override
                        public void onResponse(Call<PageResponse<UpcommingMovie>> call, Response<PageResponse<UpcommingMovie>> response) {
                            if (response.isSuccessful()){
                                requestHandler.onSucess(response.body());
                            }else {
                                requestHandler.onError(response.message());
                            }
                        }

                        @Override
                        public void onFailure(Call<PageResponse<UpcommingMovie>> call, Throwable t) {
                            requestHandler.onError(t.getMessage());
                        }
                    });
        }else {
            requestHandler.onFailure(ApiConstants.NO_INTERNET_CONNECTION);
        }

    }

    public List<UpcommingMovie> getUpcommingMovieList() {
        return upcommingMovieList;
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
