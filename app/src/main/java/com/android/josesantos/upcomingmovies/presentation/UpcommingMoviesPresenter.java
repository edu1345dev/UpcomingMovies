package com.android.josesantos.upcomingmovies.presentation;

import android.util.Log;

import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.data.entities.MovieDbConfiguration;
import com.android.josesantos.upcomingmovies.data.entities.PageResponse;
import com.android.josesantos.upcomingmovies.data.entities.UpcommingMovie;
import com.android.josesantos.upcomingmovies.model.usecase.GetGenres;
import com.android.josesantos.upcomingmovies.model.usecase.GetMovieDbConfiguration;
import com.android.josesantos.upcomingmovies.model.usecase.LoadUpcommingMovieList;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by josesantos on 04/12/17.
 */

public class UpcommingMoviesPresenter implements UpcommingMoviesContract.Presenter {
    private static final String TAG = "UpcommingMoviesPresente";

    UpcommingMoviesContract.View view;
    LoadUpcommingMovieList loadUpcommingMovieList;
    GetGenres getGenres;
    GetMovieDbConfiguration getMovieDbConfiguration;


    @Inject
    public UpcommingMoviesPresenter(LoadUpcommingMovieList loadUpcommingMovieList,
                                    GetGenres getGenres,
                                    GetMovieDbConfiguration getMovieDbConfiguration) {

        this.loadUpcommingMovieList = loadUpcommingMovieList;
        this.getGenres = getGenres;
        this.getMovieDbConfiguration = getMovieDbConfiguration;
    }


    @Override
    public void onResume(UpcommingMoviesContract.View view) {
        this.view = view;
        loadProperties();
    }

    private void loadProperties() {
        loadUpcommingMovieList.execute(new UserListObserver());
    }

    @Override
    public void onPause() {
        view = null;
        loadUpcommingMovieList.dispose();
    }

    private boolean hasViewAttached(){
        return view != null;
    }

    @Override
    public void loadUpcommingMovies() {
        view.showLoading();
        loadUpcommingMovieList.execute(new UserListObserver());
    }

    @Override
    public List<Genres.Genre> getGenresList() {
        return getGenres.execute();
    }

    @Override
    public MovieDbConfiguration getMovieDbConfig() {
        return getMovieDbConfiguration.execute();
    }

    private final class UserListObserver extends DisposableObserver<PageResponse<UpcommingMovie>> {

        @Override
        public void onNext(PageResponse<UpcommingMovie> value) {
            Log.d(TAG, "onNext: ");
            if (hasViewAttached()){
                view.onMoviesLoaded(value.getResults());
            }
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, "onError: ");
            if (hasViewAttached()){
                view.hideLoading();
            }
        }

        @Override
        public void onComplete() {
            Log.d(TAG, "onComplete: ");
            if (hasViewAttached()){
                view.hideLoading();
            }
        }
    }
}
