package com.example.networkdemo.bean

data class Stories(
    val title: String,
    val url: String,
    val hint: String,
    val images: MutableList<String>
)
