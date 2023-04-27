package com.example.networkcall

import android.app.Application
import android.content.Context
import com.example.networkcall.utils.SharedPreferencesManager

class App : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun createAttributionContext(attributionTag: String?): Context {
        return super.createAttributionContext(attributionTag)
    }


}