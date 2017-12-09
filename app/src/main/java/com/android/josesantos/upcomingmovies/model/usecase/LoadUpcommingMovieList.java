package com.android.josesantos.upcomingmovies.model.usecase;

import com.android.josesantos.upcomingmovies.data.entities.PageResponse;
import com.android.josesantos.upcomingmovies.data.entities.Movie;
import com.android.josesantos.upcomingmovies.model.repository.MoviesRepoImpl;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by josesantos on 04/12/17.
 */

public class LoadUpcommingMovieList extends BaseUseCase<PageResponse<Movie>>{

    @Inject
    MoviesRepoImpl upcommingMoviesRepo;

    @Inject
    LoadUpcommingMovieList() {
        //used by dagger
    }

    public void execute(DisposableObserver<PageResponse<Movie>> observer) {
        upcommingMoviesRepo.loadUpcommingMovies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

        addDisposable(observer);
    }
}
