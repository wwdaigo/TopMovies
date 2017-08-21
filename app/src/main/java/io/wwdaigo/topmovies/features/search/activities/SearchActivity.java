package io.wwdaigo.topmovies.features.search.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import dagger.android.AndroidInjection;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.wwdaigo.topmovies.R;
import io.wwdaigo.topmovies.databinding.ActivitySearchBinding;

public class SearchActivity extends AppCompatActivity {

    private static final int DEBOUNCE_TEXT_TIMEOUT = 1000;

    private CompositeDisposable disposables = new CompositeDisposable();
    private ActivitySearchBinding binding;

    public static Intent startIntent(Context context) {
        return  new Intent(context, SearchActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindEvents();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (!disposables.isDisposed())
            disposables.dispose();
    }

    private void bindEvents() {
        Disposable searchTextDisposable = createSearchTextChangeObservable()
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.i("Search", "SEARCH "+ s);
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
}
