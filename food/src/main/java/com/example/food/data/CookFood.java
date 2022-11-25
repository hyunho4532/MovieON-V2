package com.example.food.data;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CookFood {

    @SerializedName("total_count")
    @Expose
    private String totalCount;
    @SerializedName("row")
    @Expose
    private List<Row> row = null;
    @SerializedName("RESULT")
    @Expose
    private NameResult result;

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public List<Row> getRow() {
        return row;
    }

    public void setRow(List<Row> row) {
        this.row = row;
    }

    public NameResult getResult() {
        return result;
    }

    public void setResult(NameResult result) {
        this.result = result;
    }
}