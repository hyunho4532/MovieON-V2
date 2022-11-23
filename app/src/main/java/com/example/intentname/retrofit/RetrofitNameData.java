package com.example.intentname.retrofit;

import com.example.intentname.movieName.data.Result;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitNameData {
    @GET("http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json")
    Call<Result> getNameOffice(@Query("key") String key,
                               @Query("movieNm") String movieNm);
}