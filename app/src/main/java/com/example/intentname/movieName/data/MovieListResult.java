package com.example.intentname.movieName.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieListResult {

    @SerializedName("totCnt")
    @Expose
    private Integer totCnt;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("movieList")
    @Expose
    private List<Movie> movieList = null;

    public Integer getTotCnt() {
        return totCnt;
    }

    public void setTotCnt(Integer totCnt) {
        this.totCnt = totCnt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

}