package com.example.networkdemo.base

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.networkdemo.databinding.ItemNewsBinding

class BaseViewHolder(view: ItemNewsBinding) : RecyclerView.ViewHolder(view.root) {
    val imageView: ImageView = view.headerIv
    val title: TextView = view.titleTv
    val desc: TextView = view.descTv

}