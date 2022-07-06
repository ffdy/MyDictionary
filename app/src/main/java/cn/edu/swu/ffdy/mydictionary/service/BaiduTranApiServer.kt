package cn.edu.swu.ffdy.mydictionary.service

import cn.edu.swu.ffdy.mydictionary.data.TranData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface BaiduTranApiServer {
    @GET("api/trans/vip/translate")
    fun getTran(@Query("q")q:String, @Query("from")from:String, @Query("to")to:String,
                @Query("appid")appid: String, @Query("salt")salt: String, @Query("sign")sign: String) : Call<TranData>

//    companion object {
//        private const val BASE_URL = "http://api.fanyi.baidu.com/"
//
//        fun create(): BaiduTranApiServer {
////            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }
////
////            val client = OkHttpClient.Builder()
////                .addInterceptor(logger)
////                .build()
//
//            return Retrofit.Builder()
////                .baseUrl(BASE_URL)
////                .client(client)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(BaiduTranApiServer::class.java)
//        }
//    }
}