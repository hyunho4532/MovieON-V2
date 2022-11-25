package com.example.intentname.retrofit;

import com.example.intentname.movieName.data.Result;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitNameData {
    @GET("http://openapi.foodsafetykorea.go.kr/api/sample/COOKRCP01/json")
    Call<Result> getNameOffice(@Query("key") String key,
                               @Query("movieNm") String movieNm,
                               @Query("itemPerPage") String itemPerPage);
}