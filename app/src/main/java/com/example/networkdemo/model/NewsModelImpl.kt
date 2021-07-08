package com.example.networkdemo.model

import android.os.Handler
import android.util.Log
import com.example.networkdemo.base.BaseLoadListener
import com.example.networkdemo.bean.NewsBean
import com.example.networkdemo.bean.Result
import com.example.networkdemo.http.HttpUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.*

class NewsModelImpl : INewsModel{
    companion object {
        private const val TAG = "NewsModelImpl"
    }
    var simpleNewsBeanList: MutableList<NewsBean> = ArrayList()
    var date: String = ""
    override fun loadNewsData(page: Int, loadListener: BaseLoadListener<NewsBean>) {
        HttpUtils.getNewsData().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<Result?>() {
                override fun onNext(newsBean: Result?) {
                    Log.i(TAG, "onNext: newsBean: $newsBean")
                    simpleNewsBeanList.clear()
                    if (newsBean != null) {
                        date = newsBean.date
                        if (newsBean.stories.isNotEmpty()) { // add stories
                            for (story in newsBean.stories) {
                                val thumbnail = story.images[0]
                                val name = story.title
                                val desc = story.hint

                                val simpleNewsBean = NewsBean()
                                simpleNewsBean.name.set(name)
                                simpleNewsBean.thumbnail.set(thumbnail)
                                simpleNewsBean.description.set(desc)
                                simpleNewsBeanList.add(simpleNewsBean)
                                if (page > 1) { // 大于1页，取第一条数据
                                    return
                                }
                            }
                        }

                        if (newsBean.top_stories.isNotEmpty()) {  // add top_stories
                            for (topStory in newsBean.top_stories) {
                                val thumbnail = topStory.image
                                val name = topStory.title
                                val desc = topStory.hint

                                val simpleNewsBean = NewsBean()
                                simpleNewsBean.name.set(name)
                                simpleNewsBean.description.set(desc)
                                simpleNewsBean.thumbnail.set(thumbnail)
                                simpleNewsBeanList.add(simpleNewsBean)
                            }
                        }
                    }

                }

                override fun onStart() {
                    super.onStart()
                    Log.i(TAG, "onStart: ")
                    loadListener!!.loadStart()
                }

                override fun onError(throwable: Throwable) {
                    Log.i(TAG, "onError: " + throwable.message)
                    loadListener!!.loadFailure(throwable.message)
                }

                override fun onComplete() {
                    Log.i(TAG, "onComplete--data size : ${simpleNewsBeanList.size}")
                    Handler().postDelayed({
                        loadListener!!.loadSuccess(simpleNewsBeanList)
                        loadListener.loadComplete(date)
                    }, 2000)
                }
            })
    }
}