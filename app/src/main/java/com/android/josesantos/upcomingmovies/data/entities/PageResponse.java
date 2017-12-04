package com.android.josesantos.upcomingmovies.data.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jose Santos on 04/12/2017.
 */

public class PageResponse<T> {
    @SerializedName("results")
    @Expose
    private List<T> results = new ArrayList<>();

    @SerializedName("page")
    @Expose
    private Integer page = 1;

    @SerializedName("total_results")
    @Expose
    private Integer totalResults;

    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;

    public List<T> getResults() {
        return results;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}
