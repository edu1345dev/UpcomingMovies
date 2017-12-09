package com.android.josesantos.upcomingmovies.presentation;

import android.util.Log;

import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.data.entities.Movie;
import com.android.josesantos.upcomingmovies.data.entities.MovieConfiguration;
import com.android.josesantos.upcomingmovies.data.entities.MovieListWrapper;
import com.android.josesantos.upcomingmovies.data.entities.PageResponse;
import com.android.josesantos.upcomingmovies.model.usecase.GetGenres;
import com.android.josesantos.upcomingmovies.model.usecase.GetMovieDbConfiguration;
import com.android.josesantos.upcomingmovies.model.usecase.LoadSearchMovieList;
import com.android.josesantos.upcomingmovies.model.usecase.LoadUpcommingMovieList;
import com.android.josesantos.upcomingmovies.model.usecase.ReloadSearchMovieList;
import com.android.josesantos.upcomingmovies.model.usecase.ReloadUpcommingMovieList;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by josesantos on 04/12/17.
 */

public class UpcommingMoviesPresenter implements UpcommingMoviesContract.Presenter {
    private static final String TAG = "UpcommingMoviesPresente";

    private ReloadSearchMovieList reloadSearchMovies;
    private UpcommingMoviesContract.View view;
    private LoadUpcommingMovieList loadUpcommingMovieList;
    private LoadSearchMovieList loadSearchMovieList;
    private GetGenres getGenres;
    private GetMovieDbConfiguration getMovieDbConfiguration;
    private ReloadUpcommingMovieList reloadUpcommingMovieList;

    @Inject
    public UpcommingMoviesPresenter(LoadUpcommingMovieList loadUpcommingMovieList,
                                    LoadSearchMovieList loadSearchMovieList,
                                    GetGenres getGenres,
                                    GetMovieDbConfiguration getMovieDbConfiguration,
                                    ReloadUpcommingMovieList reloadUpcommingMovieList,
                                    ReloadSearchMovieList reloadSearchMovieList) {

        this.loadUpcommingMovieList = loadUpcommingMovieList;
        this.getGenres = getGenres;
        this.getMovieDbConfiguration = getMovieDbConfiguration;
        this.reloadUpcommingMovieList = reloadUpcommingMovieList;
        this.loadSearchMovieList = loadSearchMovieList;
        this.reloadSearchMovies = reloadSearchMovieList;
    }


    @Override
    public void onStart(UpcommingMoviesContract.View view) {
        this.view = view;
    }

    @Override
    public void onStop() {
        view.hideLoading();
        view = null;
    }

    @Override
    public void onDestroy() {
        this.loadUpcommingMovieList.dispose();
        this.reloadUpcommingMovieList.dispose();
        this.loadSearchMovieList.dispose();
        this.reloadSearchMovies.dispose();
    }

    private boolean hasViewAttached() {
        return view != null;
    }

    @Override
    public void loadUpcommingMovies() {
        if (hasViewAttached()) {
            view.showLoading();
            loadUpcommingMovieList.execute(new UserListObserver());
        }
    }

    @Override
    public void searchMovies(String query) {
        if (hasViewAttached()) {
            view.showLoading();
            loadSearchMovieList.execute(new UserListObserver(), query);
        }
    }

    @Override
    public void reloadSearchMovies(String query) {
        if (hasViewAttached()) {
            view.showLoading();
            reloadSearchMovies.execute(new UserListObserver(), query);
        }
    }

    @Override
    public void reloadUpcommingMovies() {
        if (hasViewAttached()) {
            view.showLoading();
        }
        reloadUpcommingMovieList.execute(new UserListObserver());
    }

    @Override
    public Genres getGenres() {
        return getGenres.execute();
    }

    @Override
    public MovieConfiguration getMovieDbConfig() {
        return getMovieDbConfiguration.execute();
    }

    @Override
    public MovieListWrapper getMovieWrapper() {
        Genres genres = getGenres();
        MovieConfiguration config = getMovieDbConfig();
        return new MovieListWrapper(new ArrayList<>(), genres, config);
    }

    private final class UserListObserver extends DisposableObserver<PageResponse<Movie>> {

        @Override
        public void onNext(PageResponse<Movie> value) {
            Log.d(TAG, "onNext: " + value);
            handleOnNext(value);
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, "onError: " + e);
            handleOnError(e);

        }

        @Override
        public void onComplete() {
            Log.d(TAG, "onComplete: ");
            handleOnComplete();
        }
    }

    private void handleOnComplete() {
        if (hasViewAttached()) {
            view.hideLoading();
        }
    }

    private void handleOnNext(PageResponse<Movie> value) {
        if (hasViewAttached()) {
            if (value.getPage() == 1) {
                view.onMoviesReload(value.getResults());
            } else {
                view.onMoviesLoaded(value.getResults());
            }
        }
    }

    private void handleOnError(Throwable e) {
        if (e instanceof ConnectException || e instanceof UnknownHostException) {
            if (hasViewAttached()) {
                view.hideLoading();
                view.showInternetConnectionError();
            }
        } else {
            if (hasViewAttached()) {
                view.hideLoading();
                view.showUnknownError();
            }
        }
    }
}
