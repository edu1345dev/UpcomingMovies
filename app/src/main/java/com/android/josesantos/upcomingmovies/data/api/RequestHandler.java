package com.android.josesantos.upcomingmovies.data.api;

/**
 * Created by Jose Santos on 11/10/2016.
 */


public interface RequestHandler<T>{

    void onSucess(T response);
    void onError(String message);
    void onFailure(String message);
}
