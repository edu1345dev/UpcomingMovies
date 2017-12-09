package com.android.josesantos.upcomingmovies.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.android.josesantos.upcomingmovies.AppApplication;
import com.android.josesantos.upcomingmovies.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SplashScreenActivity extends BaseActivity implements SplashScreenContract.View{
    private static final String TAG = "SplashScreenActivity";

//    @Inject
//    LoadMovieDbConfiguration loadMovieDbConfiguration;
//    @Inject
//    LoadGenres loadGenres;
//    @Inject
//    GetMovieDbConfiguration getMovieDbConfiguration;
//    @Inject
//    GetGenres getGenres;

    @Inject
    SplashScreenPresenter presenter;

    @BindView(R.id.iv_splash)
    ImageView ivSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        ButterKnife.bind(this);

        AppApplication.getAppComponent().inject(this);

        applyAnimation();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart(this);
    }

    @Override
    protected void onResume() {
        presenter.loadFirstData();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
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
                checkInternet();
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

    private void checkInternet() {
        if (!isThereInternetConnection()){
            showConnectionError();
        }
    }


    @Override
    public void showInternetConnectionError() {
        showConnectionError();
    }

    @Override
    public void showUnknownError() {
        showUnkownError();
    }

    @Override
    public void launchMainActivityDelayed() {
        Handler handler = new Handler();
        handler.postDelayed(this::launchMainActivity, 2000);
    }


    @Override
    public void launchMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    void onRetryConnection() {
        presenter.loadFirstData();
    }
}
