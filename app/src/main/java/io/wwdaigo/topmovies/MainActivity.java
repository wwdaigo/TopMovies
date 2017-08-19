package io.wwdaigo.topmovies;

import android.app.Activity;
import android.os.Bundle;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
