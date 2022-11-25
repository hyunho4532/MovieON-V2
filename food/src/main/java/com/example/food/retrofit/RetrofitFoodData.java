package com.example.food.retrofit;

import com.example.food.data.NameResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitFoodData {
    @GET("http://openapi.foodsafetykorea.go.kr/api/sample/COOKRCP01/json")
    Call<NameResult> getFoodName(@Query("keyId") String keyId,
                                 @Query("serviceId") String serviceId,
                                 @Query("dataType") String dataType,
                                 @Query("startIdx") String startIdx,
                                 @Query("endIdx") String endIdx);
}