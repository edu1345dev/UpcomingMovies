package com.android.josesantos.upcomingmovies.model.usecase;

import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.model.repository.MoviesRepoImpl;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by josesantos on 05/12/17.
 */

public class LoadGenres extends BaseUseCase<Genres>{

    @Inject
    MoviesRepoImpl upcommingMoviesRepo;

    @Inject
    LoadGenres() {
        //used by dagger
    }

    public void execute(DisposableObserver<Genres> disposableObserver){
        upcommingMoviesRepo.loadGenres()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);

        addDisposable(disposableObserver);
    }
}
