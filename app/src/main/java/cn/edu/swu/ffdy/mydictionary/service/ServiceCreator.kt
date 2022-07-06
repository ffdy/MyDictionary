package cn.edu.swu.ffdy.mydictionary.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
//    private const val BASE_URL = "https://api.binstd.com/"       //根路径
    private const val BASE_URL = "http://api.fanyi.baidu.com/"       //根路径

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())     //添加用于解析数据的转换库
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

}