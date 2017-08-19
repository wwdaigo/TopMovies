package io.wwdaigo.topmovies.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by daigomatsuoka on 19/08/17.
 */

public class BaseResponse<T> {
    private int id;
    private int page;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("total_results")
    private int totalResults;
    private List<T> results;

    public int getId() {
        return id;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public List<T> getResults() {
        return results;
    }
}