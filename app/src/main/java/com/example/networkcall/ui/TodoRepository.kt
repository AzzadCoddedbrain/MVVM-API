package com.example.networkcall.ui

import com.example.networkcall.api.RetrofitClient
import com.example.networkcall.model.NetworkResult
import com.example.networkcall.utils.Webservice
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class TodoRepository(private val client : Webservice = RetrofitClient.getInstance()) {

    suspend fun getPopularMovies() = flow {
        emit(NetworkResult.Loading())
        val response = client.getMostPopularMovies()
        emit(NetworkResult.Success(response))
    }.catch { e ->
        emit(NetworkResult.Error(e.message ?: "Unknown Error"))
    }



}