package com.example.networkcall.utils

import com.example.networkcall.model.MovieResponse
import com.example.networkcall.model.Todou
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface Webservice {


    @GET("/posts")
    suspend fun getMostPopularMovies() : Todou

}