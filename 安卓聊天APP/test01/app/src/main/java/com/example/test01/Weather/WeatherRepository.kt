package com.example.test01.Weather

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRepository {
    private val weatherApi: WeatherApi = Retrofit.Builder()
        .baseUrl("https://www.yiketianqi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WeatherApi::class.java)

    suspend fun getWeather(): WeatherResponse {
        return weatherApi.getWeather("95979945", "pg8gdx39", 1)
        //第四个参数cityId可选佛山市不同区
       // return weatherApi.getWeather("95979945", "pg8gdx39", 1, cityId)
    }
}

