package com.example.networkdemo.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, VH : RecyclerView.ViewHolder?>(mContext: Context) : RecyclerView.Adapter<VH>() {

    @JvmField
    var mList: MutableList<T> = ArrayList()

    @JvmField
    var inflater: LayoutInflater = mContext.applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    fun refreshData(data: List<T>?) {
        mList.clear()
        mList.addAll(data!!)
        notifyDataSetChanged()
    }

    fun loadMoreData(data: List<T>?) {
        mList.addAll(data!!)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return onCreateVH(parent, viewType)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        onBindVH(holder, position)
    }

    abstract fun onBindVH(holder: VH, position: Int)

    abstract fun onCreateVH(parent: ViewGroup, viewType: Int): VH
}