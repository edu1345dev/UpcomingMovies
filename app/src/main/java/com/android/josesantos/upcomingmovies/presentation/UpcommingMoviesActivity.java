package com.android.josesantos.upcomingmovies.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.android.josesantos.upcomingmovies.AppApplication;
import com.android.josesantos.upcomingmovies.R;
import com.android.josesantos.upcomingmovies.data.entities.UpcommingMovie;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by josesantos on 04/12/17.
 */

public class UpcommingMoviesActivity extends AppCompatActivity implements UpcommingMoviesContract.View{
    private static final String TAG = "UpcommingMoviesActivity";

    @Inject
    UpcommingMoviesPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        AppApplication.getAppComponent().inject(this);

        Button button = findViewById(R.id.bt_load);
        button.setOnClickListener(view -> {
            presenter.loadUpcommingMovies();
            presenter.getGenresList();
            presenter.getMovieDbConfig();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onMoviesLoaded(List<UpcommingMovie> upcommingMovieList) {
        Log.d(TAG, "onMoviesLoaded: "+upcommingMovieList);
    }
}
