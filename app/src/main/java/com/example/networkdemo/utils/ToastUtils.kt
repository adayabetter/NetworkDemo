package com.example.networkdemo.utils

import android.content.Context
import android.view.Gravity
import android.widget.Toast

object ToastUtils {
    private var toast: Toast? = null

    fun show(context: Context, msg: String?) {
        if (toast == null) {
            toast = Toast.makeText(context.applicationContext, "", Toast.LENGTH_SHORT)
        }
        toast?.setText(msg)
        toast?.setGravity(Gravity.CENTER, 0, 0)
        toast?.show()
    }
}