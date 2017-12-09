package com.android.josesantos.upcomingmovies.model.usecase;

import dagger.internal.Preconditions;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by josesantos on 06/12/17.
 */

public class BaseUseCase<T> {

    private final CompositeDisposable disposables;

    BaseUseCase() {
        this.disposables = new CompositeDisposable();
    }

    public void dispose(){
        if (!disposables.isDisposed()){
            disposables.dispose();
        }
    }

    public void addDisposable(Disposable disposable){
        Preconditions.checkNotNull(disposable);
        Preconditions.checkNotNull(disposables);
        disposables.add(disposable);
    }
}