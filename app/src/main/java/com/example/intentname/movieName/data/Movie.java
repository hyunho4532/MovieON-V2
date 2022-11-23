package com.example.intentname.movieName.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("movieCd")
    @Expose
    private String movieCd;
    @SerializedName("movieNm")
    @Expose
    private String movieNm;
    @SerializedName("movieNmEn")
    @Expose
    private String movieNmEn;
    @SerializedName("prdtYear")
    @Expose
    private String prdtYear;
    @SerializedName("openDt")
    @Expose
    private String openDt;
    @SerializedName("typeNm")
    @Expose
    private String typeNm;
    @SerializedName("prdtStatNm")
    @Expose
    private String prdtStatNm;
    @SerializedName("nationAlt")
    @Expose
    private String nationAlt;
    @SerializedName("genreAlt")
    @Expose
    private String genreAlt;
    @SerializedName("repNationNm")
    @Expose
    private String repNationNm;
    @SerializedName("repGenreNm")
    @Expose
    private String repGenreNm;
    @SerializedName("directors")
    @Expose
    private List<Director> directors = null;
    @SerializedName("companys")
    @Expose
    private List<Company> companys = null;

    public String getMovieCd() {
        return movieCd;
    }

    public void setMovieCd(String movieCd) {
        this.movieCd = movieCd;
    }

    public String getMovieNm() {
        return movieNm;
    }

    public void setMovieNm(String movieNm) {
        this.movieNm = movieNm;
    }

    public String getMovieNmEn() {
        return movieNmEn;
    }

    public void setMovieNmEn(String movieNmEn) {
        this.movieNmEn = movieNmEn;
    }

    public String getPrdtYear() {
        return prdtYear;
    }

    public void setPrdtYear(String prdtYear) {
        this.prdtYear = prdtYear;
    }

    public String getOpenDt() {
        return openDt;
    }

    public void setOpenDt(String openDt) {
        this.openDt = openDt;
    }

    public String getTypeNm() {
        return typeNm;
    }

    public void setTypeNm(String typeNm) {
        this.typeNm = typeNm;
    }

    public String getPrdtStatNm() {
        return prdtStatNm;
    }

    public void setPrdtStatNm(String prdtStatNm) {
        this.prdtStatNm = prdtStatNm;
    }

    public String getNationAlt() {
        return nationAlt;
    }

    public void setNationAlt(String nationAlt) {
        this.nationAlt = nationAlt;
    }

    public String getGenreAlt() {
        return genreAlt;
    }

    public void setGenreAlt(String genreAlt) {
        this.genreAlt = genreAlt;
    }

    public String getRepNationNm() {
        return repNationNm;
    }

    public void setRepNationNm(String repNationNm) {
        this.repNationNm = repNationNm;
    }

    public String getRepGenreNm() {
        return repGenreNm;
    }

    public void setRepGenreNm(String repGenreNm) {
        this.repGenreNm = repGenreNm;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public List<Company> getCompanys() {
        return companys;
    }

    public void setCompanys(List<Company> companys) {
        this.companys = companys;
    }

}