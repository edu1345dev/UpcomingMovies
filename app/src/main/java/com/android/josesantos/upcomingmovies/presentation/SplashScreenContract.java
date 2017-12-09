package com.android.josesantos.upcomingmovies.presentation;

/**
 * Created by josesantos on 08/12/17.
 */

public class SplashScreenContract {
    public interface View {
        void showInternetConnectionError();

        void showUnknownError();

        void launchMainActivityDelayed();

        void launchMainActivity();
    }

    public interface Presenter {
        void onStart(SplashScreenContract.View view);

        void onStop();

        void onDestroy();

        void loadGenres();

        void loadMovieDbConfig();

        void loadFirstData();
    }
}
