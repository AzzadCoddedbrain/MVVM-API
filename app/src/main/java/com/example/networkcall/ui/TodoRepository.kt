package com.example.networkcall.ui

import com.example.networkcall.api.RetrofitClient
import com.example.networkcall.model.NetworkResult
import com.example.networkcall.utils.Webservice
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class TodoRepository(private val client: Webservice = RetrofitClient.retrofit) {

    suspend fun getPopularMovies(id: String) = flow {
        emit(NetworkResult.Loading(true))
        val response = client.getMostPopularMovies()
        emit(NetworkResult.Success(response))
    }.catch { e ->
        emit(NetworkResult.Failure(e.message ?: "Unknown Error"))
    }

}