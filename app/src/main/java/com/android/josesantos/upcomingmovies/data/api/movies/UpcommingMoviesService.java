package com.android.josesantos.upcomingmovies.data.api.movies;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.josesantos.upcomingmovies.data.api.constants.ApiConstants;
import com.android.josesantos.upcomingmovies.data.api.ApiService;
import com.android.josesantos.upcomingmovies.data.api.constants.LanguageConstants;
import com.android.josesantos.upcomingmovies.data.api.RequestHandler;
import com.android.josesantos.upcomingmovies.data.entities.UpcommingMovie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by josesantos on 03/12/17.
 */

public class UpcommingMoviesService extends ApiService {

    private final Context context;
    private List<UpcommingMovie> upcommingMovieList = new ArrayList<>();

    public UpcommingMoviesService(Context context) {
        super();
        this.context = context;
    }

    public void getUpcommingMoviesList(String page, final RequestHandler<List<UpcommingMovie>> requestHandler){
        if (isThereInternetConnection()){
            createService(UpcommingMoviesRoutes.class)
                    .getUpcommingMoviesList(page, LanguageConstants.EN_US, ApiConstants.API_KEY)
                    .enqueue(new Callback<List<UpcommingMovie>>() {
                        @Override
                        public void onResponse(Call<List<UpcommingMovie>> call, Response<List<UpcommingMovie>> response) {
                            if (response.isSuccessful()){
                                upcommingMovieList.addAll(response.body());
                                requestHandler.onSucess(response.body());
                            }else {
                                requestHandler.onError(response.body().toString());
                            }
                        }

                        @Override
                        public void onFailure(Call<List<UpcommingMovie>> call, Throwable t) {
                            requestHandler.onFailure(t.getMessage());
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
