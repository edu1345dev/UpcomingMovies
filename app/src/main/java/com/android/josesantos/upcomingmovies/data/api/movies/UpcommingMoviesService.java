package com.android.josesantos.upcomingmovies.data.api.movies;

import com.android.josesantos.upcomingmovies.data.api.constants.ApiConstants;
import com.android.josesantos.upcomingmovies.data.api.ApiService;
import com.android.josesantos.upcomingmovies.data.api.constants.LanguageConstants;
import com.android.josesantos.upcomingmovies.data.api.RequestHandler;
import com.android.josesantos.upcomingmovies.data.entities.UpcommingMovie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by josesantos on 03/12/17.
 */

public class UpcommingMoviesService extends ApiService {

    public void getUpcommingMoviesList(String page, final RequestHandler<List<UpcommingMovie>> requestHandler){
        createService(UpcommingMoviesRoutes.class)
                .getUpcommingMoviesList(page, LanguageConstants.EN_US, ApiConstants.API_KEY)
                .enqueue(new Callback<List<UpcommingMovie>>() {
                    @Override
                    public void onResponse(Call<List<UpcommingMovie>> call, Response<List<UpcommingMovie>> response) {
                        if (response.isSuccessful()){
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
    }
}
