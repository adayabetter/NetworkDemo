package com.example.networkdemo.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.networkdemo.BR
import com.example.networkdemo.R
import com.example.networkdemo.base.BaseAdapter
import com.example.networkdemo.base.BaseViewHolder
import com.example.networkdemo.bean.NewsBean

class NewsAdapter(context: Context) : BaseAdapter<NewsBean, BaseViewHolder<*>>(context) {
    override fun onBindVH(holder: BaseViewHolder<*>, position: Int) {
        holder.binding.setVariable(BR.newsBean, mList[position])
        holder.binding.executePendingBindings()
    }

    override fun onCreateVH(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val dataBinding = DataBindingUtil.inflate<ViewDataBinding>(inflater, R.layout.item_news, parent, false)
        return BaseViewHolder(dataBinding)
    }
}