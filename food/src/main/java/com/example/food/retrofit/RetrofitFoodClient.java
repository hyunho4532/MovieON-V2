package com.example.food.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFoodClient {
    private static RetrofitFoodClient instance = null;
    private static RetrofitFoodData retrofitFoodData;

    public RetrofitFoodClient() {
        String base_url = "http://www.foodsafetykorea.go.kr";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitFoodData = retrofit.create(RetrofitFoodData.class);
    }

    public static RetrofitFoodClient getInstance() {
        if (instance == null) {
            instance = new RetrofitFoodClient();
        }

        return instance;
    }

    public static RetrofitFoodData getRetrofitFoodData() {
        return retrofitFoodData;
    }
}
