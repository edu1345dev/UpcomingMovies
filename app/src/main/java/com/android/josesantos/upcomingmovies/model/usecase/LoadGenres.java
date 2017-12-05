package com.android.josesantos.upcomingmovies.model.usecase;

import com.android.josesantos.upcomingmovies.model.repository.UpcommingMoviesRepoImpl;
import javax.inject.Inject;

/**
 * Created by josesantos on 05/12/17.
 */

public class LoadGenres {

    @Inject
    UpcommingMoviesRepoImpl upcommingMoviesRepo;

    @Inject
    public LoadGenres() {
    }

    public void execute(){
        upcommingMoviesRepo.loadGenres();
    }
}
