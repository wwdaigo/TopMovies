package io.wwdaigo.topmovies.features.search.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.wwdaigo.topmovies.commons.listeners.OnSelectMovieData;
import io.wwdaigo.topmovies.data.MovieData;
import io.wwdaigo.topmovies.databinding.SearchListItemBinding;
import rx.functions.Action1;

/**
 * Created by marcelo.matsuoka on 21/08/17.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<MovieData> movieDataList;
    private OnSelectMovieData onSelectMovieData;

    public SearchAdapter() {
        movieDataList = new ArrayList<>();
    }

    public void setObservable(Observable<List<MovieData>> observable) {
        observable.subscribe(new Consumer<List<MovieData>>() {
            @Override
            public void accept(@NonNull List<MovieData> movieDatum) throws Exception {
                SearchAdapter.this.movieDataList = movieDatum;
                SearchAdapter.this.notifyDataSetChanged();
            }
        });
    }

    public void setOnSelectMovieData(OnSelectMovieData onSelectMovieData) {
        this.onSelectMovieData = onSelectMovieData;
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

        public void bind(final MovieData movieData) {
            binding.setMovieData(movieData);

            if (onSelectMovieData != null) {
                RxView.clicks(binding.getRoot()).subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        SearchAdapter.this.onSelectMovieData.selectMovieData(movieData);
                    }
                });
            }
        }
    }
}
