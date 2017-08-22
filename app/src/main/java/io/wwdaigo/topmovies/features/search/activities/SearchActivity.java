package io.wwdaigo.topmovies.features.search.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.AndroidInjection;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.wwdaigo.topmovies.R;
import io.wwdaigo.topmovies.commons.listeners.OnSelectMovieData;
import io.wwdaigo.topmovies.data.MovieData;
import io.wwdaigo.topmovies.databinding.ActivitySearchBinding;
import io.wwdaigo.topmovies.features.search.adapters.SearchAdapter;
import io.wwdaigo.topmovies.features.search.viewmodels.SearchViewModelType;
import io.wwdaigo.topmovies.router.MainRouterType;

public class SearchActivity extends AppCompatActivity implements OnSelectMovieData {

    @Inject
    SearchViewModelType searchViewModel;

    @Inject
    SearchAdapter searchAdapter;

    @Inject
    MainRouterType mainRouter;

    @Inject
    @Named("searchActivityCompositeDisposable")
    CompositeDisposable disposables;

    private static final int DEBOUNCE_TEXT_TIMEOUT = 1000;

    private ActivitySearchBinding binding;

    public static Intent startIntent(Context context) {
        return  new Intent(context, SearchActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);

        bindRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindEvents();
        bindOutputs();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (!disposables.isDisposed())
            disposables.dispose();
    }

    private void bindEvents() {
        Disposable searchTextDisposable = createSearchTextChangeObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        searchViewModel.getInputs().search(s);
                    }
                }).subscribe();

        disposables.addAll(searchTextDisposable);
    }

    private Observable<String> createSearchTextChangeObservable() {
        Observable<String> textChangeObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                final TextWatcher watcher = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) { /**/ }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) { /**/ }

                    @Override
                    public void afterTextChanged(Editable s) {
                        emitter.onNext(s.toString());
                    }
                };

                binding.keywordsEditText.addTextChangedListener(watcher);

                emitter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Exception {
                        binding.keywordsEditText.removeTextChangedListener(watcher);
                    }
                });
            }
        });

        return textChangeObservable
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String query) throws Exception {
                        return query.length() >= 2;
                    }
                })
                .debounce(DEBOUNCE_TEXT_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    private void bindOutputs() {
        Disposable isLoadingDisposable = searchViewModel.getOutputs().isLoading().subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean isLoading) throws Exception {
                binding.loadingProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            }
        });

        disposables.addAll(isLoadingDisposable);
    }

    private void bindRecyclerView() {
        searchAdapter.setObservable(searchViewModel.getOutputs().searchResult());
        searchAdapter.setOnSelectMovieData(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.searchRecyclerView.setLayoutManager(layoutManager);
        binding.searchRecyclerView.setHasFixedSize(true);

        binding.searchRecyclerView.setAdapter(searchAdapter);
    }

    @Override
    public void selectMovieData(MovieData movieData) {
        mainRouter.openMovie(this, movieData);
    }
}
