package com.example.intentname.movieName.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("movieListResult")
    @Expose
    private MovieListResult movieListResult;

    public MovieListResult getMovieListResult() {
        return movieListResult;
    }

    public void setMovieListResult(MovieListResult movieListResult) {
        this.movieListResult = movieListResult;
    }

}