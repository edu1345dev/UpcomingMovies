package com.android.josesantos.upcomingmovies.presentation;

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
import com.android.josesantos.upcomingmovies.data.entities.Movie;
import com.android.josesantos.upcomingmovies.data.entities.MovieWrapper;
import com.android.josesantos.upcomingmovies.presentation.MainActivity;
import com.android.josesantos.upcomingmovies.presentation.UpcommingMoviesFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
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
    @BindView(R.id.iv_backgroud)
    ImageView ivBackground;

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
        Movie movie = movieWrapper.getMovie();

        RequestOptions requestOptions = new RequestOptions()
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop();

        if (movie.getPosterCompleteUrl(movieWrapper.getMovieConfiguration()) !=null){
            Glide.with(this)
                    .setDefaultRequestOptions(requestOptions)
                    .load(movie.getPosterCompleteUrl(movieWrapper.getMovieConfiguration()))
                    .into(ivBackground);
        }

        Glide.with(this)
                .load(movie.getPosterCompleteUrl(movieWrapper.getMovieConfiguration()))
                .into(ivPoster);

        if (movie.getTitle() != null){
            tvName.setText(movie.getTitle());
        }else {
            tvName.setText(getString(R.string.no_name_informed));
        }

        if (movie.getReleaseDate() != null){
            tvRelease.setText(movie.getReleaseDate());
        }else {
            tvRelease.setText(getString(R.string.no_release_date_informed));
        }

        if (!movie.getGenreIds().isEmpty()){
            tvGenres.setText(movieWrapper.getGenres().getGenresText(movie.getGenreIds()));
        }else {
            tvGenres.setText(getString(R.string.no_genres_informed));
        }

        if (movie.getOverview() != null){
            tvOverview.setText(movie.getOverview());
        }else {
            tvOverview.setText(getString(R.string.no_overview_informed));
        }
    }

}
