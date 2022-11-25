package com.example.food.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NameResult {

    @SerializedName("COOKRCP01")
    @Expose
    private CookFood cookFood;

    public CookFood getCookFood() {
        return cookFood;
    }

    public void setCookFood(CookFood cookFood) {
        this.cookFood = cookFood;
    }
}