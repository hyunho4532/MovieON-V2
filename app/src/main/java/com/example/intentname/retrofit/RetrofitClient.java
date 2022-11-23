package com.example.intentname.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient instance = null;
    private static RetrofitNameData retrofitNameData;

    private RetrofitClient() {
        String baseUrl = "http://www.kobis.or.kr";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitNameData = retrofit.create(RetrofitNameData.class);
    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public static RetrofitNameData getRetrofitNameData() {
        return retrofitNameData;
    }
}
