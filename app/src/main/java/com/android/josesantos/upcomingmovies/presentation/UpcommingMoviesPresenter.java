package com.android.josesantos.upcomingmovies.presentation;

import android.util.Log;

import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.data.entities.MovieDbConfiguration;
import com.android.josesantos.upcomingmovies.data.entities.MovieWrapper;
import com.android.josesantos.upcomingmovies.data.entities.PageResponse;
import com.android.josesantos.upcomingmovies.data.entities.Movie;
import com.android.josesantos.upcomingmovies.model.usecase.GetGenres;
import com.android.josesantos.upcomingmovies.model.usecase.GetMovieDbConfiguration;
import com.android.josesantos.upcomingmovies.model.usecase.LoadUpcommingMovieList;
import com.android.josesantos.upcomingmovies.model.usecase.ReloadUpcommingMovieList;

import java.util.ArrayList;

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
    ReloadUpcommingMovieList reloadUpcommingMovieList;

    @Inject
    public UpcommingMoviesPresenter(LoadUpcommingMovieList loadUpcommingMovieList,
                                    GetGenres getGenres,
                                    GetMovieDbConfiguration getMovieDbConfiguration,
                                    ReloadUpcommingMovieList reloadUpcommingMovieList) {

        this.loadUpcommingMovieList = loadUpcommingMovieList;
        this.getGenres = getGenres;
        this.getMovieDbConfiguration = getMovieDbConfiguration;
        this.reloadUpcommingMovieList = reloadUpcommingMovieList;
    }


    @Override
    public void onResume(UpcommingMoviesContract.View view) {
        this.view = view;
    }

    @Override
    public void onPause() {
        view = null;
    }

    @Override
    public void onDestroy() {
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
    public void reloadUpcommingMovies() {
        reloadUpcommingMovieList.execute(new UserListObserver());
    }

    @Override
    public Genres getGenres() {
        return getGenres.execute();
    }

    @Override
    public MovieDbConfiguration getMovieDbConfig() {
        return getMovieDbConfiguration.execute();
    }

    @Override
    public MovieWrapper getMovieWrapper() {
        Genres genres = getGenres();
        MovieDbConfiguration config = getMovieDbConfig();
        return new MovieWrapper(new ArrayList<>(), genres, config);
    }

    private final class UserListObserver extends DisposableObserver<PageResponse<Movie>> {

        @Override
        public void onNext(PageResponse<Movie> value) {
            Log.d(TAG, "onNext: ");
            if (hasViewAttached()){
                view.onMoviesLoaded(value.getResults());
            }
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, "onError: "+e);
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
