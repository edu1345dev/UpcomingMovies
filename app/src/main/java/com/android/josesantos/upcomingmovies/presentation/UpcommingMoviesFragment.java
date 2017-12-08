package com.android.josesantos.upcomingmovies.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.josesantos.upcomingmovies.AppApplication;
import com.android.josesantos.upcomingmovies.R;
import com.android.josesantos.upcomingmovies.data.entities.Movie;
import com.android.josesantos.upcomingmovies.presentation.adapter.MovieDetailsActivity;
import com.android.josesantos.upcomingmovies.presentation.adapter.OnMovieClickListener;
import com.android.josesantos.upcomingmovies.presentation.adapter.UpcommingMoviesAdapter;
import com.android.josesantos.upcomingmovies.presentation.custom.EndlessRecyclerViewScrollListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Jose Santos on 27/10/2016.
 */

public class UpcommingMoviesFragment extends BaseFragment implements UpcommingMoviesContract.View {
    private static final String TAG = "UpcommingMoviesFragment";

    private UpcommingMoviesAdapter mAdapter;
    private EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;

    @BindView(R.id.sr_upcomming_movies)
    SwipeRefreshLayout swipeRefresh;

    @BindView(R.id.rv_upcomming_movies)
    RecyclerView mRecycler;

    @BindView(R.id.search_view)
    SearchView searchView;

    @BindView(R.id.not_found_container)
    LinearLayout notFoundContainer;

    @BindView(R.id.tv_not_found_container)
    TextView tvNotFound;

    @Inject
    UpcommingMoviesPresenter presenter;

    public static String MOVIE = "movie";
    public static String MOVIE_LIST = "movie_list";

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

        AppApplication.getAppComponent().inject(this);

        //instancia as views
        recyclerInit();

        //configura a swipe refresh view com seus listeners
        configureSwipeRefresh();

        configureSearchView();

        if (savedInstanceState != null) {
            handleOnSavedInstance(savedInstanceState);
        }

        return view;
    }

    private void handleOnSavedInstance(Bundle savedInstanceState) {
        Gson gson = new Gson();
        String moviesList = savedInstanceState.getString(MOVIE_LIST);

        Type listType = new TypeToken<ArrayList<Movie>>() {
        }.getType();
        mAdapter.setMovieList(gson.fromJson(moviesList, listType));

    }

    public void recyclerInit() {

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        mRecycler.setLayoutManager(llm);

        endlessRecyclerViewScrollListener = new EndlessRecyclerViewScrollListener(llm) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                String search = searchView.getQuery().toString();

                if (!search.equals("")){
                    presenter.searchMovies(search);
                }else {
                    presenter.loadUpcommingMovies();
                }
            }
        };

        mRecycler.addOnScrollListener(endlessRecyclerViewScrollListener);

        mAdapter = new UpcommingMoviesAdapter(presenter.getMovieWrapper(), getContext());
        mAdapter.setOnMovieClickListener(onMovieClickListener);
        mRecycler.setAdapter(mAdapter);
    }

    public void configureSwipeRefresh() {
        swipeRefresh.setColorSchemeResources(R.color.colorPrimaryDark, R.color.colorAccent);
        swipeRefresh.setOnRefreshListener(() -> {
            onUserRefreshesList();
            endlessRecyclerViewScrollListener.resetState();
        });
    }

    public void configureSearchView() {

        searchView.setQueryHint(getString(R.string.hint_search));
        searchView.setIconifiedByDefault(false);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.reloadSearchMovies(query);
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //do nothing
                return false;
            }
        });

        final ImageView close = searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        close.setOnClickListener(view -> {
            searchView.setQuery("", false);
            searchView.clearFocus();
            presenter.reloadUpcommingMovies();
        });

    }

    private void onUserRefreshesList() {
        if (!searchView.getQuery().equals("")) {
            searchView.setQuery("", false);
        }

        presenter.reloadUpcommingMovies();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Gson gson = new Gson();
        outState.putString(MOVIE_LIST, gson.toJson(mAdapter.getMovieList()));
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdapter.getMovieList().isEmpty()) {
            presenter.loadUpcommingMovies();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onResume(this);
    }

    @Override
    public void onStop() {
        super.onStop();
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
        if (!swipeRefresh.isRefreshing()){
            swipeRefresh.setRefreshing(true);
        }
    }

    @Override
    public void hideLoading() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onMoviesLoaded(List<Movie> movieList) {
        mAdapter.setMovieList(movieList);
    }

    @Override
    public void showInternetConnectionError() {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).showConnectionError();
        }
    }

    @Override
    public void showUnknownError() {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).showUnkownError();
        }
    }

    @Override
    public void onMoviesReload(List<Movie> movieList) {
        if (movieList.isEmpty()){
            showEmptyContainer();
        }else {
            hideNotFoundContainer();
            mAdapter.getMovieList().clear();
            mAdapter.setMovieList(movieList);
            mAdapter.notifyDataSetChanged();
        }
    }

    private void showEmptyContainer() {
        mRecycler.setVisibility(View.GONE);
        notFoundContainer.setVisibility(View.VISIBLE);
        tvNotFound.setText(getString(R.string.no_results_found, searchView.getQuery().toString()));
    }

    private void hideNotFoundContainer(){
        mRecycler.setVisibility(View.VISIBLE);
        notFoundContainer.setVisibility(View.GONE);
    }

    @Override
    public void retryConnection() {
        presenter.reloadUpcommingMovies();
        endlessRecyclerViewScrollListener.resetState();
    }
}
