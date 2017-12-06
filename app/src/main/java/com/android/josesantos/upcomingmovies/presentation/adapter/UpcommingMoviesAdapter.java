package com.android.josesantos.upcomingmovies.presentation.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.josesantos.upcomingmovies.R;
import com.android.josesantos.upcomingmovies.data.entities.MovieDbConfiguration;
import com.android.josesantos.upcomingmovies.data.entities.UpcommingMovie;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.List;

/**
 * Created by josesantos on 06/12/17.
 */

public class UpcommingMoviesAdapter extends RecyclerView.Adapter<UpcommingMoviesAdapter.UpcommingMovieView> {
    private static final String TAG = "UpcommingMoviesAdapter";
    MovieDbConfiguration movieDbConfiguration;
    List<UpcommingMovie> upcommingMovieList;
    Context context;

    public UpcommingMoviesAdapter(MovieDbConfiguration movieDbConfiguration, List<UpcommingMovie> upcommingMovieList, Context context) {
        this.movieDbConfiguration = movieDbConfiguration;
        this.upcommingMovieList = upcommingMovieList;
        this.context = context;
    }

    @Override
    public UpcommingMovieView onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UpcommingMovieView(LayoutInflater.from(context)
                .inflate(R.layout.item_view_upcomming_movie, parent, false));
    }

    @Override
    public void onBindViewHolder(UpcommingMovieView holder, int position) {
        UpcommingMovie upcommingMovie = upcommingMovieList.get(position);

        String url = movieDbConfiguration.getImages().getBaseUrl()
                + movieDbConfiguration.getImages().getBackdropSizes().get(1)
                + upcommingMovie.getBackdropPath();

        Log.d(TAG, "onBindViewHolder: URL"+url);


        RequestOptions requestOptions = new RequestOptions()
                .placeholder(context.getResources().getDrawable(R.drawable.place_holder_movie))
                .priority(Priority.NORMAL)
                .error(context.getResources().getDrawable(R.drawable.place_holder_error))
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);

        Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(url)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.d(TAG, "onLoadFailed: "+e.getCause());
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(holder.background);
    }

    @Override
    public int getItemCount() {
        return upcommingMovieList.size();
    }

    public List<UpcommingMovie> getUpcommingMovieList() {
        return upcommingMovieList;
    }

    public void setUpcommingMovieList(List<UpcommingMovie> upcommingMovieList) {
        this.upcommingMovieList.addAll(upcommingMovieList);
        notifyItemRangeChanged(this.upcommingMovieList.size(), upcommingMovieList.size());
    }

    public class UpcommingMovieView extends RecyclerView.ViewHolder {

        ImageView background;

        public UpcommingMovieView(View itemView) {
            super(itemView);

            background = itemView.findViewById(R.id.imageView);
        }
    }
}
