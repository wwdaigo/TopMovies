package io.wwdaigo.topmovies.features.movielist.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.wwdaigo.topmovies.R;
import io.wwdaigo.topmovies.data.MovieData;
import io.wwdaigo.topmovies.databinding.MovieListCardItemBinding;

/**
 * Created by daigomatsuoka on 19/08/17.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieItemViewHolder> {

    private List<MovieData> movieDataList;

    public MovieListAdapter(Observable<List<MovieData>> observableMovieDataList) {

        movieDataList = new ArrayList<>();
        observableMovieDataList
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MovieData>>() {
                    @Override
                    public void accept(@NonNull List<MovieData> movieDatum) throws Exception {
                        MovieListAdapter.this.movieDataList = movieDatum;
                        MovieListAdapter.this.notifyDataSetChanged();
                    }
                });

    }

    @Override
    public MovieItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MovieListCardItemBinding movieListCardItemBinding =
                MovieListCardItemBinding.inflate(layoutInflater, parent, false);

        return new MovieItemViewHolder(movieListCardItemBinding);
    }

    @Override
    public void onBindViewHolder(MovieItemViewHolder holder, int position) {
        MovieData movieData = movieDataList.get(position);
        holder.bind(movieData);
    }

    @Override
    public int getItemCount() {
        return movieDataList.size();
    }

    class MovieItemViewHolder extends RecyclerView.ViewHolder {

        private MovieListCardItemBinding binding;

        public MovieItemViewHolder(MovieListCardItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }

        public void bind (MovieData movieData) {
            binding.setMovieData(movieData);
            Picasso.with(binding.getRoot().getContext())
                    .load(movieData.getPosterPath())
                    .into(binding.movieCoverImage, new Callback() {
                        @Override
                        public void onSuccess() {
                            binding.loadingImageProgressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            binding.movieCoverImage.setImageResource(R.drawable.error);
                            binding.loadingImageProgressBar.setVisibility(View.GONE);
                        }
                    });
        }
    }
}