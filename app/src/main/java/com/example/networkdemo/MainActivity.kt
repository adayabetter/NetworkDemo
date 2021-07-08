package com.example.networkdemo

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkdemo.adapter.NewsAdapter
import com.example.networkdemo.base.IBaseView
import com.example.networkdemo.constant.Constant
import com.example.networkdemo.databinding.ActivityMainBinding
import com.example.networkdemo.databinding.RecyclerviewHeaderBinding
import com.example.networkdemo.viewmodel.NewsVM
import com.jcodecraeer.xrecyclerview.ProgressStyle
import com.jcodecraeer.xrecyclerview.XRecyclerView

class MainActivity : AppCompatActivity(), XRecyclerView.LoadingListener, IBaseView{
    private lateinit var binding: ActivityMainBinding
    private lateinit var headerView: RecyclerviewHeaderBinding
    private lateinit var adapter: NewsAdapter
    private lateinit var newsVM: NewsVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        addHeaderView()
        newsVM = NewsVM(this, adapter)
        listenDataChanged()
    }

    private fun initRecyclerView() {
        binding.newsRv.setRefreshProgressStyle(ProgressStyle.BallClipRotatePulse)
        binding.newsRv.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotatePulse)
        binding.newsRv.setLoadingListener(this)
        val linearLayoutManager = LinearLayoutManager(this)
        adapter = NewsAdapter(this)
        binding.newsRv.layoutManager = linearLayoutManager
        binding.newsRv.adapter = adapter
    }

    private fun addHeaderView() {
        headerView = RecyclerviewHeaderBinding.inflate(layoutInflater, binding.newsRv, false)
        binding.newsRv.addHeaderView(headerView.root)
    }

    private fun listenDataChanged() {
        val dateText: TextView = headerView.headerTv
        val dateObserver = androidx.lifecycle.Observer<String> {
            dateText.text = "知乎日报： $it"
        }
        newsVM.ZhiHuDate.observe(this, dateObserver)
    }

    override fun onRefresh() {
        newsVM.loadRefreshData()
    }

    override fun onLoadMore() {
        newsVM.loadMoreData()
    }

    override fun loadStart(loadType: Int) {
        if (loadType == Constant.LoadData.FIRST_LOAD) {
        }
    }

    override fun loadComplete() {
        binding.newsRv.loadMoreComplete()
        binding.newsRv.refreshComplete()
    }

    override fun loadFailure(message: String) {
        binding.newsRv.loadMoreComplete()
        binding.newsRv.refreshComplete()
    }
}