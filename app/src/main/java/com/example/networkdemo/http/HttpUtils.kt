package com.example.networkdemo.http

import com.example.networkdemo.bean.Result
import com.example.networkdemo.constant.UrlConstant
import com.example.networkdemo.retrofitinterface.RetrofitInterface
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object HttpUtils {
    private const val DEFAULT_TIMEOUT = 8 //连接 超时的时间，单位：秒

    private val client =
        OkHttpClient.Builder().connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(
                DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS
            ).writeTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS).build()


    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(UrlConstant.URL_BASE)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }


    fun getNewsData(): Observable<Result?> {
        val request = buildService(RetrofitInterface::class.java)
        return request.newsData
    }

}