package com.android.josesantos.upcomingmovies.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.josesantos.upcomingmovies.AppApplication;
import com.android.josesantos.upcomingmovies.R;
import com.android.josesantos.upcomingmovies.data.entities.Movie;
import com.android.josesantos.upcomingmovies.data.entities.MovieWrapper;
import com.android.josesantos.upcomingmovies.presentation.adapter.MovieDetailsActivity;
import com.android.josesantos.upcomingmovies.presentation.adapter.OnMovieClickListener;
import com.android.josesantos.upcomingmovies.presentation.adapter.UpcommingMoviesAdapter;
import com.android.josesantos.upcomingmovies.presentation.custom.EndlessRecyclerViewScrollListener;
import com.google.gson.Gson;

import java.time.Duration;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jose Santos on 27/10/2016.
 */

public class UpcommingMoviesFragment extends Fragment implements UpcommingMoviesContract.View {
    private static final String TAG = "UpcommingMoviesFragment";

    private UpcommingMoviesAdapter mAdapter;

    @BindView(R.id.sr_upcomming_movies)
    SwipeRefreshLayout swipeRefresh;

    @BindView(R.id.rv_upcomming_movies)
    RecyclerView mRecycler;

    @Inject
    UpcommingMoviesPresenter presenter;

    public static String MOVIE = "movie";

    private OnMovieClickListener onMovieClickListener =
            movieWrapper -> {
                Gson gson = new Gson();
                String movie = gson.toJson(movieWrapper);

                Intent it = new Intent(getActivity(), MovieDetailsActivity.class);
                it.putExtra(MOVIE, movie);
                startActivity(it);
            };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcomming_movies, viewGroup, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppApplication.getAppComponent().inject(this);

        //instancia as views
        recyclerInit();

        //configura a swipe refresh view com seus listeners
        configureSwipeRefresh();
    }

    public void recyclerInit() {

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        mRecycler.setLayoutManager(llm);

        mRecycler.addOnScrollListener(new EndlessRecyclerViewScrollListener(llm) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                Log.d(TAG, "onLoadMore: ");
                presenter.loadUpcommingMovies();
            }
        });

        mAdapter = new UpcommingMoviesAdapter(presenter.getMovieWrapper(), getContext());
        mAdapter.setOnMovieClickListener(onMovieClickListener);
        mRecycler.setAdapter(mAdapter);
    }

    public void configureSwipeRefresh() {
        swipeRefresh.setColorSchemeResources(R.color.colorPrimaryDark, R.color.colorAccent);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onUserRefreshesList();
            }
        });
    }

    private void onUserRefreshesList() {
        mAdapter.getMovieList().clear();
        presenter.reloadUpcommingMovies();
    }

    @Override
    public void onDestroyView() {
        if (mRecycler != null) {
            mRecycler.setAdapter(null);
            mRecycler = null;
        }

        mRecycler = null;

        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume(this);
        if (mAdapter.getMovieList().isEmpty()) {
            presenter.loadUpcommingMovies();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showLoading() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onMoviesLoaded(List<Movie> movieList) {
        mAdapter.setMovieList(movieList);
        Log.d(TAG, "onMoviesLoaded: " + mAdapter.getMovieList().size());
    }
}
