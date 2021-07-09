package com.example.networkdemo.retrofitinterface

import com.example.networkdemo.bean.Result
import com.example.networkdemo.constant.UrlConstant
import io.reactivex.Observable
import retrofit2.http.GET

interface RetrofitInterface {
    //获取“知乎日报”的数据
    @get:GET(UrlConstant.URL_LAST)
    val newsData: Observable<Result?>
}