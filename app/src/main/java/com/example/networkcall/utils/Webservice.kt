package com.example.networkcall.utils

import com.example.networkcall.model.Todou
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface Webservice {
//    https://reqres.in/api/unknown/23
//    @GET("/posts")
    @GET("api/unknown/23")
   suspend fun getTodo(): Todou
}