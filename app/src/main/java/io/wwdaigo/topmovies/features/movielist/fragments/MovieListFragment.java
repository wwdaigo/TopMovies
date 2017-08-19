package io.wwdaigo.topmovies.features.movielist.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.wwdaigo.topmovies.R;
import io.wwdaigo.topmovies.features.movielist.viewmodels.MovieListViewModelType;

public class MovieListFragment extends Fragment {

    @Inject
    MovieListViewModelType viewModel;

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
        viewModel.getInputs().loadMovies();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false);
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

}
