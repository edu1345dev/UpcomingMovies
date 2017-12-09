package com.android.josesantos.upcomingmovies.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.android.josesantos.upcomingmovies.AppApplication;
import com.android.josesantos.upcomingmovies.R;
import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.data.entities.MovieConfiguration;
import com.android.josesantos.upcomingmovies.model.usecase.GetGenres;
import com.android.josesantos.upcomingmovies.model.usecase.GetMovieDbConfiguration;
import com.android.josesantos.upcomingmovies.model.usecase.LoadGenres;
import com.android.josesantos.upcomingmovies.model.usecase.LoadMovieDbConfiguration;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.observers.DisposableObserver;


public class SplashScreenActivity extends BaseActivity {
    private static final String TAG = "SplashScreenActivity";

    @Inject
    LoadMovieDbConfiguration loadMovieDbConfiguration;
    @Inject
    LoadGenres loadGenres;
    @Inject
    GetMovieDbConfiguration getMovieDbConfiguration;
    @Inject
    GetGenres getGenres;

    @BindView(R.id.iv_splash)
    ImageView ivSplash;

    private boolean isMovieDbConfigCached = false;
    private boolean isGenresCached = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        ButterKnife.bind(this);

        AppApplication.getAppComponent().inject(this);

        applyAnimation();

        loadFirstData();
    }

    @Override
    protected void onDestroy() {
        loadMovieDbConfiguration.dispose();
        loadGenres.dispose();
        super.onDestroy();
    }

    private void applyAnimation() {
        Animation zoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        Animation zoomOut = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        zoomIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //do nothing
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivSplash.startAnimation(zoomOut);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //do nothing
            }
        });

        zoomOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //do nothing
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivSplash.startAnimation(zoomIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //do nothing
            }
        });

        ivSplash.setImageDrawable(getDrawable(R.drawable.arqtouch_logo));
        ivSplash.startAnimation(zoomIn);
    }

    private void loadFirstData() {
        if (isConfigInfoCached()) {
            launchMainActivityDelayed();
        } else if (isThereInternetConnection()) {
            loadMovieDbConfig();
        } else {
            showConnectionError();
        }
    }

    private void launchMainActivityDelayed() {
        Handler handler = new Handler();
        handler.postDelayed(this::launchMainActivity, 2000);
    }

    private boolean isConfigInfoCached() {
        isMovieDbConfigCached = getMovieDbConfiguration.execute() != null;
        isGenresCached = getGenres.execute() != null;
        return isMovieDbConfigCached && isGenresCached;
    }

    private void loadMovieDbConfig() {
        loadMovieDbConfiguration.execute(new DisposableObserver<MovieConfiguration>() {
            @Override
            public void onNext(MovieConfiguration value) {
                Log.d(TAG, "onNext: load configuration file");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ", e.getCause());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: finished loading configuration file");
                loadGenres();
            }
        });
    }

    private void loadGenres() {
        loadGenres.execute(new DisposableObserver<Genres>() {
            @Override
            public void onNext(Genres value) {
                Log.d(TAG, "onNext: load genres");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ", e.getCause());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: finished loading genres");
                launchMainActivity();
            }
        });
    }

    private void launchMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    void onRetryConnection() {
        loadFirstData();
    }
}
