package com.example.networkcall.ui

import android.util.Log
import androidx.lifecycle.*
import com.example.networkcall.model.MovieResponse
import com.example.networkcall.model.NetworkResult
import com.example.networkcall.model.Todou
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TodoViewmodel(val repository: TodoRepository = TodoRepository()) : ViewModel() {

    var  movieResponse = MutableLiveData<NetworkResult<Todou>>()



    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        Log.e("TAG", ": "+throwable.printStackTrace() )
    }

    fun callApi(id:String){
        viewModelScope.launch(Dispatchers.IO){
            repository.getPopularMovies(id).collectLatest {
                movieResponse.postValue(it)
            }
        }
    }
    /*
    var firstTodo: LiveData<MovieResponse> = liveData(Dispatchers.IO + coroutineExceptionHandler) {

        viewModelScope.launch {
            repository.getPopularMovies(id).collect {
                movieResponse.postValue(it)
            }
        }

//        val retrivedTodo = repository.myflow
        val retrivedTodo = repository.getPopularMovies(id)

        retrivedTodo.collect {
//            movieResponse.postValue(it)
        firstTodo = movieResponse.postValue(it)

        }

    }*/



}