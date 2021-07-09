package com.example.networkdemo.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageUtil {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(iv: ImageView, url: String?) {
        Glide.with(iv.context).load(url).into(iv)
    }
}