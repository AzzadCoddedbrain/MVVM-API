package com.example.networkcall.ui

import androidx.lifecycle.*
import com.example.networkcall.model.NetworkResult
import com.example.networkcall.model.Todou
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TodoViewmodel(val repository: TodoRepository = TodoRepository()) : ViewModel() {

    var movieResponse = MutableLiveData<NetworkResult<Todou>>()

    // call api with params
    fun callApi()  : MutableLiveData<NetworkResult<Todou>> {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPopularMovies().collectLatest {
                movieResponse.postValue(it)
            }
        }
        return movieResponse

    }

}