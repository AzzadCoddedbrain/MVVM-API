package com.example.networkcall.ui

import androidx.lifecycle.*
import com.example.networkcall.model.NetworkResult
import com.example.networkcall.model.Todou
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.wait

class TodoViewmodel(val repository: TodoRepository = TodoRepository()) : ViewModel() {

    // resposne

    // call api with params
    fun callApi(id:String)  : MutableLiveData<NetworkResult<Todou>>  {
        var  movieResponse = MutableLiveData<NetworkResult<Todou>>()

        viewModelScope.launch(Dispatchers.IO){
            repository.getPopularMovies(id).collectLatest {
                movieResponse.postValue(it)
            }
        }
        return movieResponse
    }

}