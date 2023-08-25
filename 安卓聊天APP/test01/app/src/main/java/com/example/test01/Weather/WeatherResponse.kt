package com.example.test01.Weather

//不一定都有数据返回，所以带‘？’号。具体所需参数根据网址设置。
data class WeatherResponse(
    val nums: Int?,
    val cityid: String?,
    val city: String?,
    val date: String?,
    val week: String?,
    val update_time: String?,
    val wea: String?,
    val wea_img: String?,
    val tem: String?,
    val tem_day: String?,
    val tem_night: String?,
    val win: String?,
    val win_speed: String?,
    val win_meter: String?,
    val air: String?,
    val pressure: String?,
    val humidity: String?
)
