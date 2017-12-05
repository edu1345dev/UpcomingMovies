package com.android.josesantos.upcomingmovies.presentation;

import android.util.Log;

import com.android.josesantos.upcomingmovies.data.entities.PageResponse;
import com.android.josesantos.upcomingmovies.data.entities.UpcommingMovie;
import com.android.josesantos.upcomingmovies.model.usecase.LoadUpcommingMovieList;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by josesantos on 04/12/17.
 */

public class UpcommingMoviesPresenter implements UpcommingMoviesContract.Presenter {
    private static final String TAG = "UpcommingMoviesPresente";

    UpcommingMoviesContract.View view;
    LoadUpcommingMovieList loadUpcommingMovieList;

    @Inject
    public UpcommingMoviesPresenter(LoadUpcommingMovieList loadUpcommingMovieList) {
        this.loadUpcommingMovieList = loadUpcommingMovieList;
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
    }

    private boolean hasViewAttached(){
        return view != null;
    }

    @Override
    public void loadUpcommingMovies() {
        loadUpcommingMovieList.execute(new UserListObserver());
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
        }

        @Override
        public void onComplete() {
            Log.d(TAG, "onComplete: ");
        }
    }
}
