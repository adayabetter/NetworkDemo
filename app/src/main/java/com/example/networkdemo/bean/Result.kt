package com.example.networkdemo.bean

data class Result(
    val stories: MutableList<Stories>,
    val top_stories: MutableList<TopStories>,
    val date: String
)
