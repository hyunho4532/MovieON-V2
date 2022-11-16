package com.example.intentname.retrofit;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @GET("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")
    Call<Map<String, Object>> getBoxOffice(@Query("key") String key,
                                           @Query("targetDt") String targetDt,
                                           @Query("rankInten") int rankInten);
}