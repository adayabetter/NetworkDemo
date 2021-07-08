package com.example.networkdemo.base

interface IBaseView {
    fun loadStart(loadType: Int)
    fun loadComplete()
    fun loadFailure(message: String)
}