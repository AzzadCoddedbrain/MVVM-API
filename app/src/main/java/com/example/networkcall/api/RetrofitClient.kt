package com.example.networkcall.api

import android.util.Log
import com.example.networkcall.utils.Constant
import com.example.networkcall.utils.Webservice
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitClient {
    companion object {

        private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS) //retrofit default 10 seconds
            .writeTimeout(30, TimeUnit.SECONDS) //retrofit default 10 seconds
            .readTimeout(30, TimeUnit.SECONDS) //retrofit default 10 seconds
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//        .addInterceptor(BasicAuthInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(Webservice::class.java)
    }

    class AuthTokenInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder().header("Authorization", "AuthToken")
            val request = requestBuilder.build()
            Log.i("TAG", "intercept: "+request.body )
            return chain.proceed(request)
    }
}

}
