package com.example.networkcall.api

import android.util.Log
import com.example.networkcall.utils.Constant
import com.example.networkcall.utils.Webservice
import com.google.gson.GsonBuilder
import com.squareup.leakcanary.fragments.android_support.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitClient {

    fun getInstance(): Webservice {
        return Retrofit.Builder().baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(client)
            .build().create(Webservice::class.java)
    }

    val client = OkHttpClient().newBuilder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.HEADERS }
        )
        .build()
}



