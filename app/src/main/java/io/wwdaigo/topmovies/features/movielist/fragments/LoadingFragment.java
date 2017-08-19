package io.wwdaigo.topmovies.features.movielist.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.wwdaigo.topmovies.R;

public class LoadingFragment extends Fragment {

    public LoadingFragment() {
        // Required empty public constructor
    }

    public static LoadingFragment newInstance() {
        return new LoadingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loading, container, false);
    }

}