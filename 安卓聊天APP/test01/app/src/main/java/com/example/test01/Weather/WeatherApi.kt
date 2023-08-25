package com.example.test01.Weather

import retrofit2.http.GET
import retrofit2.http.Query

//根据获取的网址格式所需传递的参数调整
interface WeatherApi {
    @GET("free/day")
    suspend fun getWeather(
        @Query("appid") appId: String,
        @Query("appsecret") appSecret: String,
        @Query("unescape") unescape: Int
        //@Query("cityid") cityId: String
    ): WeatherResponse
}

