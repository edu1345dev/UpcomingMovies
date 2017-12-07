package com.android.josesantos.upcomingmovies.model.usecase;

import com.android.josesantos.upcomingmovies.data.entities.MovieConfiguration;
import com.android.josesantos.upcomingmovies.model.repository.UpcommingMoviesRepoImpl;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by josesantos on 05/12/17.
 */

public class LoadMovieDbConfiguration extends BaseUseCase<MovieConfiguration>{

    @Inject
    UpcommingMoviesRepoImpl upcommingMoviesRepo;

    @Inject
    public LoadMovieDbConfiguration() {
    }

    public void execute(DisposableObserver<MovieConfiguration> disposableObserver){
        upcommingMoviesRepo.loadMovieDbConfiguration()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);

        addDisposable(disposableObserver);
    }
}
