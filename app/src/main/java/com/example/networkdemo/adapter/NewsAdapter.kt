package com.example.networkdemo.adapter

import android.content.Context
import android.view.ViewGroup
import com.example.networkdemo.base.BaseAdapter
import com.example.networkdemo.base.BaseViewHolder
import com.example.networkdemo.bean.NewsBean
import com.example.networkdemo.databinding.ItemNewsBinding
import com.example.networkdemo.utils.ImageUtil

class NewsAdapter(context: Context) : BaseAdapter<NewsBean, BaseViewHolder>(context) {
    override fun onBindVH(holder: BaseViewHolder, position: Int) {
        val newsBean = mList[position]
        bindData(holder, newsBean)
    }

    override fun onCreateVH(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = ItemNewsBinding.inflate(inflater)
        return BaseViewHolder(view)
    }

    private fun bindData(holder: BaseViewHolder, newsBean: NewsBean) {
        if (holder != null && newsBean != null) {
            ImageUtil.loadImage(holder.imageView, newsBean.thumbnail.get())
            holder.title.text = newsBean.name.get()
            holder.desc.text = newsBean.description.get()
        }
    }
}