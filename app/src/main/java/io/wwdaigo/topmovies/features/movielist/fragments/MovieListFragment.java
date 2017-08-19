package io.wwdaigo.topmovies.features.movielist.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.wwdaigo.topmovies.R;
import io.wwdaigo.topmovies.databinding.FragmentMovieListBinding;
import io.wwdaigo.topmovies.features.movielist.adapters.MovieListAdapter;
import io.wwdaigo.topmovies.features.movielist.viewmodels.MovieListViewModelType;

public class MovieListFragment extends Fragment {

    @Inject
    MovieListViewModelType viewModel;

    @Inject
    MovieListAdapter movieListAdapter;

    FragmentMovieListBinding binding;

    private OnMovieListFragmentInteraction mListener;

    public MovieListFragment() {
        // Required empty public constructor
    }

    public static MovieListFragment newInstance() {
        MovieListFragment fragment = new MovieListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel.getOutputs().isLoading().subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean aBoolean) throws Exception {
                Log.i("ISSS", ""+ aBoolean);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_movie_list, container, false);

        bindMovieListRecyclerView();
        viewModel.getInputs().loadMovies();

        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        AndroidInjection.inject(this);

        if (context instanceof OnMovieListFragmentInteraction) {
            mListener = (OnMovieListFragmentInteraction) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnMovieListFragmentInteraction");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void bindMovieListRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), numberOfColumns());
        movieListAdapter.setObservableList(viewModel.getOutputs().listMovieData());

        binding.movieListRecyclerView.setHasFixedSize(true);
        binding.movieListRecyclerView.setLayoutManager(gridLayoutManager);
        binding.movieListRecyclerView.setAdapter(movieListAdapter);
    }

    private int numberOfColumns() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int minCols = getActivity().getResources().getInteger(R.integer.poster_min_cols);
        int widthDivider = getActivity().getResources().getInteger(R.integer.poster_width);
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        if (nColumns < minCols) return minCols;
        return nColumns;
    }
}
