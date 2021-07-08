package com.example.networkdemo.bean

import androidx.databinding.ObservableField

class NewsBean {
    @JvmField
    var thumbnail = ObservableField<String>()
    @JvmField
    var description = ObservableField<String>()
    @JvmField
    var name = ObservableField<String>()
}