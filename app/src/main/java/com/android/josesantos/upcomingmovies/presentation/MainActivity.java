package com.android.josesantos.upcomingmovies.presentation;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.android.josesantos.upcomingmovies.AppApplication;
import com.android.josesantos.upcomingmovies.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by josesantos on 04/12/17.
 */

public class MainActivity extends BaseActivity{
    private static final String TAG = "MainActivity";

    private BaseFragment currentFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppApplication.getAppComponent().inject(this);

        setContentView(R.layout.app_bar_dashboard);

        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            loadHomeFragment();
        }
    }

    private void loadHomeFragment() {
        Fragment fragment = getHomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, "");
        fragmentTransaction.commitAllowingStateLoss();
    }

    private Fragment getHomeFragment() {

        UpcommingMoviesFragment homeFragment = new UpcommingMoviesFragment();
        currentFragment = homeFragment;
        return currentFragment;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    void onRetryConnection() {
        if (currentFragment == null){
            loadHomeFragment();
        }else {
            currentFragment.retryConnection();
        }
    }
}
