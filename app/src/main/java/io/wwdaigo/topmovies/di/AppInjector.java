package io.wwdaigo.topmovies.di;

import io.wwdaigo.topmovies.App;
import io.wwdaigo.topmovies.di.components.DaggerAppComponent;
import io.wwdaigo.topmovies.di.modules.AppModule;

/**
 * Created by marcelo.matsuoka on 21/08/17.
 */

public class AppInjector {

    private AppInjector() {}

    public static void init(App app) {
        DaggerAppComponent.builder()
                .application(app)
                .appModule(new AppModule(app))
                .build()
                .inject(app);
    }
}
