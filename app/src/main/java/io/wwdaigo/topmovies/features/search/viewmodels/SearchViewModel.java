package io.wwdaigo.topmovies.features.search.viewmodels;

/**
 * Created by daigomatsuoka on 21/08/17.
 */

public class SearchViewModel implements SearchViewModelType, SearchViewModelInputs, SearchViewModelOutputs {

    @Override
    public SearchViewModelInputs getInputs() {
        return this;
    }

    @Override
    public SearchViewModelOutputs getOutputs() {
        return this;
    }
}
