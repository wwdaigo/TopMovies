package io.wwdaigo.topmovies.features.details.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.wwdaigo.topmovies.R;
import io.wwdaigo.topmovies.data.MovieData;
import io.wwdaigo.topmovies.databinding.FragmentDetailsBinding;

public class DetailsFragment extends Fragment {

    private static final String ARG_MOVIE_DATA = "movieData";
    private MovieData movieData;

    private FragmentDetailsBinding binding;

    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance(MovieData movieData) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_MOVIE_DATA, movieData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            movieData = (MovieData) getArguments().getSerializable(ARG_MOVIE_DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_details, container, false);

        if (movieData != null)
            binding.setMovieData(movieData);

        return binding.getRoot();
    }

}
