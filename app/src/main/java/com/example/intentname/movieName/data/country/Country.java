package com.example.intentname.movieName.data.country;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("korean")
    @Expose
    private List<String> korean = null;
    @SerializedName("japan")
    @Expose
    private List<String> japan = null;
    @SerializedName("american")
    @Expose
    private List<String> american = null;
    @SerializedName("china")
    @Expose
    private List<String> china = null;
    @SerializedName("germany")
    @Expose
    private List<String> germany = null;
    @SerializedName("spain")
    @Expose
    private List<String> spain = null;
    @SerializedName("tiwan")
    @Expose
    private List<String> tiwan = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getKorean() {
        return korean;
    }

    public void setKorean(List<String> korean) {
        this.korean = korean;
    }

    public List<String> getJapan() {
        return japan;
    }

    public void setJapan(List<String> japan) {
        this.japan = japan;
    }

    public List<String> getAmerican() {
        return american;
    }

    public void setAmerican(List<String> american) {
        this.american = american;
    }

    public List<String> getChina() {
        return china;
    }

    public void setChina(List<String> china) {
        this.china = china;
    }

    public List<String> getGermany() {
        return germany;
    }

    public void setGermany(List<String> germany) {
        this.germany = germany;
    }

    public List<String> getSpain() {
        return spain;
    }

    public void setSpain(List<String> spain) {
        this.spain = spain;
    }

    public List<String> getTiwan() {
        return tiwan;
    }

    public void setTiwan(List<String> tiwan) {
        this.tiwan = tiwan;
    }
}