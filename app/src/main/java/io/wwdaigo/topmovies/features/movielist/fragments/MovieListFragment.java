package io.wwdaigo.topmovies.features.movielist.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
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

    private FragmentMovieListBinding binding;
    private CompositeDisposable disposable;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_movie_list, container, false);

        bindMovieListRecyclerView();
        viewModel.getInputs().loadTopRatedMovies();

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

        disposable = new CompositeDisposable();
        bindOutputs();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mListener = null;
        disposable.clear();
    }

    private void bindOutputs() {
        Disposable loadingDisposable = viewModel.getOutputs().isLoading().subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean isLoading) throws Exception {
                mListener.toggleLoadingMode(isLoading);
            }
        });

        Disposable titleStringResourceDisposable = viewModel.getOutputs().getTitleStringResource().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer res) throws Exception {
                mListener.setActivityTitle(res);
            }
        });

        disposable.addAll(loadingDisposable, titleStringResourceDisposable);
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

    public void toggleList(int optionId) {
        switch (optionId) {
            case R.id.menu_main_popular:
                viewModel.getInputs().loadMostPopularMovies();
                break;

            case R.id.menu_main_top_rated:
                viewModel.getInputs().loadTopRatedMovies();
                break;
        }
    }
}
