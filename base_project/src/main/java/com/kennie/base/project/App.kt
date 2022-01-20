package com.kennie.base.project

import android.app.Application

open class App : Application() {

    companion object {
        private lateinit var app: App

        // @JvmStatic 加上后可以直接 getInstance()，不然需要加上Companion
        @JvmStatic
        fun getInstance(): App {
            return app
        }
    }

    override fun onCreate() {
        super.onCreate()
        app = this

    }
}