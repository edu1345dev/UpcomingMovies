package com.android.josesantos.upcomingmovies.presentation.adapter;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.josesantos.upcomingmovies.R;
import com.android.josesantos.upcomingmovies.data.entities.MovieWrapper;
import com.android.josesantos.upcomingmovies.presentation.MainActivity;
import com.android.josesantos.upcomingmovies.presentation.UpcommingMoviesFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends AppCompatActivity {

    @BindView(R.id.iv_poster)
    ImageView ivPoster;
    @BindView(R.id.tv_details_name)
    TextView tvName;
    @BindView(R.id.tv_details_release)
    TextView tvRelease;
    @BindView(R.id.tv_details_genres)
    TextView tvGenres;
    @BindView(R.id.tv_details_overview)
    TextView tvOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        ButterKnife.bind(this);

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

        tvName.setText(movieWrapper.getMovie().getTitle());
        tvRelease.setText(movieWrapper.getMovie().getReleaseDate());
        tvGenres.setText(movieWrapper.getGenres().getGenresText(movieWrapper.getMovie().getGenreIds()));
        tvOverview.setText(movieWrapper.getMovie().getOverview());
    }

}
