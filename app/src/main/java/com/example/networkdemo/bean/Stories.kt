package com.example.networkdemo.bean

/**
 * @author admin
 * @version 1.0
 * @Description 实体类
 * @date 2021-07-15
 */

data class Stories (
    
    val title: String,
	val url: String,
	val hint: String,
	val images: MutableList<String>
	
    
)
