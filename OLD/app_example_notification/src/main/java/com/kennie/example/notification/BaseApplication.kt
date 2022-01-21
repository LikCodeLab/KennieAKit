package com.kennie.example.notification

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class BaseApplication : Application() {

    init {
        mContext = this
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var mContext: Context
        fun getContext() = mContext
    }
}