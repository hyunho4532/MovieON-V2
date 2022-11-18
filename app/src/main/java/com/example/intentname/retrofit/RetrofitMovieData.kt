package com.example.intentname.retrofit

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitMovieData {
    @GET("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")
    fun getBoxOffice(@Query("key") key: String,
                     @Query("targetDt") targetDt: String,
                     @Query("rankInten") rankInten: Int)
}