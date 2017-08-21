package io.wwdaigo.topmovies.features.search.viewmodels;

import io.wwdaigo.topmovies.commons.viewmodels.ViewModel;

/**
 * Created by daigomatsuoka on 21/08/17.
 */

public class SearchViewModel extends ViewModel implements SearchViewModelType, SearchViewModelInputs, SearchViewModelOutputs {

    @Override
    public SearchViewModelInputs getInputs() {
        return this;
    }

    @Override
    public SearchViewModelOutputs getOutputs() {
        return this;
    }

    public SearchViewModel() {
        super();
    }
}
