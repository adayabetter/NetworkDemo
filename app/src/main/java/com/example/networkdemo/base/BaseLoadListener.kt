package com.example.networkdemo.base

interface BaseLoadListener<T> {
    fun loadStart()
    fun loadComplete(date: String)
    fun loadSuccess(list: List<T>)
    fun loadFailure(message: String?)
}