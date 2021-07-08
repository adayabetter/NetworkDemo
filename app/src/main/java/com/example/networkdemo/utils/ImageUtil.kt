package com.example.networkdemo.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

object ImageUtil {

    fun loadImage(iv: ImageView, url: String?) {
        Glide.with(iv.context).load(url).into(iv)
    }
}