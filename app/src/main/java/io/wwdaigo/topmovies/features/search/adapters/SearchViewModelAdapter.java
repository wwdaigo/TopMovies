package io.wwdaigo.topmovies.features.search.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.wwdaigo.topmovies.data.MovieData;
import io.wwdaigo.topmovies.databinding.SearchListItemBinding;

/**
 * Created by marcelo.matsuoka on 21/08/17.
 */

public class SearchViewModelAdapter extends RecyclerView.Adapter<SearchViewModelAdapter.SearchViewHolder> {

    private List<MovieData> movieDataList;

    public SearchViewModelAdapter() {
        movieDataList = new ArrayList<>();
    }

    public void setObservable(Observable<List<MovieData>> observable) {
        observable.subscribe(new Consumer<List<MovieData>>() {
            @Override
            public void accept(@NonNull List<MovieData> movieDatum) throws Exception {
                SearchViewModelAdapter.this.movieDataList = movieDatum;
                SearchViewModelAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        SearchListItemBinding binding = SearchListItemBinding.inflate(inflater, parent, false);
        return new SearchViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        MovieData movieData = movieDataList.get(position);
        holder.bind(movieData);
    }

    @Override
    public int getItemCount() {
        return movieDataList.size();
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {

        private SearchListItemBinding binding;

        public SearchViewHolder(SearchListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(MovieData movieData) {
            binding.setMovieData(movieData);
        }
    }
}
