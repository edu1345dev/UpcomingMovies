package com.android.josesantos.upcomingmovies.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.josesantos.upcomingmovies.R;
import com.android.josesantos.upcomingmovies.data.entities.Genres;
import com.android.josesantos.upcomingmovies.data.entities.MovieConfiguration;
import com.android.josesantos.upcomingmovies.data.entities.MovieListWrapper;
import com.android.josesantos.upcomingmovies.data.entities.Movie;
import com.android.josesantos.upcomingmovies.data.entities.MovieWrapper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

/**
 * Created by josesantos on 06/12/17.
 */

public class UpcommingMoviesAdapter extends RecyclerView.Adapter<UpcommingMoviesAdapter.UpcommingMovieView> {
    private static final String TAG = "UpcommingMoviesAdapter";
    private final Genres genres;
    MovieConfiguration movieConfiguration;
    List<Movie> movieList;
    Context context;
    OnMovieClickListener onMovieClickListener;

    public UpcommingMoviesAdapter(MovieListWrapper movieListWrapper, Context context) {
        this.movieConfiguration = movieListWrapper.getMovieConfiguration();
        this.movieList = movieListWrapper.getMovieList();
        this.context = context;
        this.genres = movieListWrapper.getGenres();
    }

    public void setOnMovieClickListener(OnMovieClickListener onMovieClickListener) {
        this.onMovieClickListener = onMovieClickListener;
    }

    @Override
    public UpcommingMovieView onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UpcommingMovieView(LayoutInflater.from(context)
                .inflate(R.layout.item_view_upcomming_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(UpcommingMovieView holder, int position) {
        Movie movie = movieList.get(position);

        String url = movie.getBackdropCompleteUrl(movieConfiguration);

        //if there is no backdrop, tries to use poster
        if (url == null){
            url = movie.getPosterCompleteUrl(movieConfiguration);
        }

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(context.getResources().getDrawable(R.drawable.place_holder_movie))
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop();

        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(url)
                .into(holder.background);

        if (movie.getTitle() != null){
            holder.name.setText(movie.getTitle());
        }else {
            holder.name.setText(R.string.no_name_informed);
        }

        if (!movie.getGenreIds().isEmpty()){
            holder.genres.setText(genres.getGenresText(movie.getGenreIds()));
        }else {
            holder.genres.setText(R.string.no_genres_informed);
        }

        if (movie.getReleaseDate() != null){
            holder.release.setText(movie.getReleaseDate());
        }else {
            holder.release.setText(R.string.no_release_date_informed);
        }

        holder.itemView.setOnClickListener(view -> {
            MovieWrapper movieWrapper = new MovieWrapper(movieList.get(position), genres, movieConfiguration);
            onMovieClickListener.onClick(movieWrapper);
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList.addAll(movieList);
        notifyItemRangeChanged(this.movieList.size(), movieList.size());
    }

    public class UpcommingMovieView extends RecyclerView.ViewHolder {

        ImageView background;
        TextView name;
        TextView genres;
        TextView release;

        public UpcommingMovieView(View itemView) {
            super(itemView);

            background = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.tv_movie_name);
            genres = itemView.findViewById(R.id.tv_movie_genres);
            release = itemView.findViewById(R.id.tv_movie_release);
        }
    }
}
