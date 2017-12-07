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
import com.android.josesantos.upcomingmovies.data.entities.MovieDbConfiguration;
import com.android.josesantos.upcomingmovies.data.entities.MovieWrapper;
import com.android.josesantos.upcomingmovies.data.entities.Movie;
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
    MovieDbConfiguration movieDbConfiguration;
    List<Movie> movieList;
    Context context;

    public UpcommingMoviesAdapter(MovieWrapper movieWrapper, Context context) {
        this.movieDbConfiguration = movieWrapper.getMovieDbConfiguration();
        this.movieList = movieWrapper.getMovieList();
        this.context = context;
        this.genres = movieWrapper.getGenres();
    }

    @Override
    public UpcommingMovieView onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UpcommingMovieView(LayoutInflater.from(context)
                .inflate(R.layout.item_view_upcomming_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(UpcommingMovieView holder, int position) {
        Movie movie = movieList.get(position);

        String url = movieDbConfiguration.getImages().getBaseUrl()
                + movieDbConfiguration.getImages().getBackdropSizes().get(1)
                + movie.getBackdropPath();

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(context.getResources().getDrawable(R.drawable.place_holder_movie))
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop();

        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(url)
                .into(holder.background);

        holder.name.setText(movie.getTitle());
        holder.genres.setText(genres.getGenresText(movie.getGenreIds()));
        holder.release.setText(movie.getReleaseDate());

        // TODO: 07/12/17 remove on final version
        if (movie.getGenreIds().isEmpty()) {
            holder.genres.append("  NO GENRES");
        }

        if (movie.getBackdropPath() == null) {
            holder.genres.append("  NO BACKDROP");
        }

        if (movie.getPosterPath() == null) {
            holder.genres.append("  NO POSTER");
        }
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
