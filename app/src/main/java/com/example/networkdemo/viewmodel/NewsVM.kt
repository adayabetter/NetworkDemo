package com.example.networkdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.networkdemo.adapter.NewsAdapter
import com.example.networkdemo.base.BaseLoadListener
import com.example.networkdemo.base.IBaseView
import com.example.networkdemo.bean.NewsBean
import com.example.networkdemo.constant.Constant
import com.example.networkdemo.model.INewsModel
import com.example.networkdemo.model.NewsModelImpl

class NewsVM(private val mIBaseView: IBaseView, private val mAdapter: NewsAdapter) : BaseLoadListener<NewsBean>{
    val newsModel: INewsModel = NewsModelImpl()
    var currentPage: Int = 1 //当前页数
    var loadType: Int = 0 //加载数据的类型
    var ZhiHuDate: MutableLiveData<String> = MutableLiveData()
    private val newsData: Unit
     private get() {
        loadType = Constant.LoadData.FIRST_LOAD
        newsModel.loadNewsData(currentPage, this)
    }

    /**
     * 获取下拉刷新的数据
     */
    fun loadRefreshData() {
        loadType = Constant.LoadData.REFRESH
        currentPage = 1
        newsModel.loadNewsData(currentPage, this)
    }

    /**
     * 获取上拉加载更多的数据
     */
    fun loadMoreData() {
        loadType = Constant.LoadData.LOAD_MORE
        currentPage++
        newsModel.loadNewsData(currentPage, this)
    }

    override fun loadStart() {
        mIBaseView.loadStart(loadType)
    }

    override fun loadComplete(date: String) {
        mIBaseView.loadComplete()
        ZhiHuDate.value = date
    }

    override fun loadSuccess(list: List<NewsBean>) {
        if (currentPage > 1) {
            mAdapter.loadMoreData(list)
        } else {
            mAdapter.refreshData(list)
        }
    }

    override fun loadFailure(message: String?) {
        // 加载失败后的提示
        if (currentPage > 1) {
            //加载失败需要回到加载之前的页数
            currentPage--
        }
        mIBaseView.loadFailure(message?: "")
    }

    init {
        newsData
    }
}