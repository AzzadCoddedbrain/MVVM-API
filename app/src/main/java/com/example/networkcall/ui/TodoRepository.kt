package com.example.networkcall.ui

import android.util.Log
import com.example.networkcall.api.RetrofitClient
import com.example.networkcall.utils.Webservice
import kotlinx.coroutines.flow.flow

class TodoRepository(private val client: Webservice=RetrofitClient.retrofit) {

//    var client: Webservice = RetrofitClient.retrofit

    suspend fun getTodo() = client.getTodo()

    var myflow = flow {
        Log.e("TAG", ": "+getTodo() )
        emit(getTodo())
    }
}