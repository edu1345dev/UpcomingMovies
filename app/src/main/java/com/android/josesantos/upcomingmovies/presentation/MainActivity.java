package com.android.josesantos.upcomingmovies.presentation;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.android.josesantos.upcomingmovies.AppApplication;
import com.android.josesantos.upcomingmovies.R;
import com.android.josesantos.upcomingmovies.presentation.custom.BaseFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by josesantos on 04/12/17.
 */

public class MainActivity extends BaseActivity implements OnTabReselectListener, OnTabSelectListener {
    private static final String TAG = "MainActivity";
    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_UPCOMMING = "upcomming";
    private static final String TAG_MOVIES = "movies";

    public static String CURRENT_TAG = TAG_UPCOMMING;

    private Handler mHandler;
    private BaseFragment currentFragment;

    @BindView(R.id.bottom_bar)
    BottomBar bottomBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppApplication.getAppComponent().inject(this);

        setContentView(R.layout.app_bar_dashboard);

        // TODO: 06/12/17 add to base view
        ButterKnife.bind(this);

        instantiateViews();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_UPCOMMING;
            loadHomeFragment();
        }
    }

    private void instantiateViews() {
        mHandler = new Handler();
        setUpBottomBar();
    }

    public void setUpBottomBar() {
        bottomBar.setOnTabSelectListener(this);
        bottomBar.setOnTabReselectListener(this);
    }

    private void loadHomeFragment() {

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app

        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.container, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }
    }

    private Fragment getHomeFragment() {

        switch (navItemIndex) {
            case 0:
                // ic_nav_home
                UpcommingMoviesFragment homeFragment = new UpcommingMoviesFragment();
                currentFragment = homeFragment;
                return homeFragment;
            case 1:
                // ic_nav_procurar
//                StockFragment StockFragment = new StockFragment();
//                StockFragment.setActivityInterface(this);
//                createToolbar(toolbar, "ESTOQUE");
//                return StockFragment;
            default:
                return new Fragment();
        }

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
    public void onTabReSelected(int tabId) {

    }

    @Override
    public void onTabSelected(int tabId) {
        switch (tabId) {
            case R.id.tab_upcomming:
                if (navItemIndex != 0) {
                    navItemIndex = 0;
                    CURRENT_TAG = TAG_UPCOMMING;
                    //invalidateOptionsMenu();
                    loadHomeFragment();
                }
                break;
            case R.id.tab_movies:
                if (navItemIndex != 1) {
                    navItemIndex = 1;
                    CURRENT_TAG = TAG_MOVIES;
                    loadHomeFragment();
                }
                break;
            default:
                navItemIndex = 0;
        }
    }

    @Override
    void onRetryConnection() {
        currentFragment.retryConnection();
    }
}
