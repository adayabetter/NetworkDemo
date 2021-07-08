package com.example.networkdemo.model

import com.example.networkdemo.base.BaseLoadListener
import com.example.networkdemo.bean.NewsBean

interface INewsModel {
    fun loadNewsData(page: Int, loadListener: BaseLoadListener<NewsBean>)
}