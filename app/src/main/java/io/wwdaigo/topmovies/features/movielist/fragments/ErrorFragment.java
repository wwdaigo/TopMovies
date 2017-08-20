package io.wwdaigo.topmovies.features.movielist.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding.view.RxView;

import io.wwdaigo.topmovies.R;
import io.wwdaigo.topmovies.databinding.FragmentErrorBinding;
import rx.functions.Action1;

public class ErrorFragment extends Fragment {

    private OnErrorFragmentInteraction mListener;
    private FragmentErrorBinding binding;

    public ErrorFragment() {
        // Required empty public constructor
    }

    public static ErrorFragment newInstance() {
        return new ErrorFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_error, container, false);

        bindEvents();
        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnErrorFragmentInteraction) {
            mListener = (OnErrorFragmentInteraction) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnErrorFragmentInteraction");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void bindEvents() {
        RxView.clicks(binding.retryButton).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                if (mListener != null) {
                    mListener.retryAction();
                }
            }
        });
    }
}
