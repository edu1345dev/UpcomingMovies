package com.android.josesantos.upcomingmovies.presentation.adapter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.android.josesantos.upcomingmovies.R;
import com.android.josesantos.upcomingmovies.data.entities.MovieWrapper;
import com.android.josesantos.upcomingmovies.presentation.MainActivity;
import com.android.josesantos.upcomingmovies.presentation.UpcommingMoviesFragment;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends AppCompatActivity {

    @BindView(R.id.iv_poster)
    ImageView ivPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (getIntent().hasExtra(UpcommingMoviesFragment.MOVIE)){
            String movie = getIntent().getStringExtra(UpcommingMoviesFragment.MOVIE);
            Gson gson = new Gson();

            MovieWrapper movieWrapper = gson.fromJson(movie, MovieWrapper.class);
            initViews(movieWrapper);
        }
    }

    private void initViews(MovieWrapper movieWrapper) {
        Glide.with(this)
                .load(movieWrapper.getMovie().getPosterCompleteUrl(movieWrapper.getMovieConfiguration()))
                .into(ivPoster);
    }

}
