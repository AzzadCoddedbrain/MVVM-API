package com.example.networkcall.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.networkcall.model.Todou
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers

class TodoViewmodel(val repository: TodoRepository = TodoRepository()) : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        Log.e("TAG", ": "+throwable.printStackTrace() )
    }

    val firstTodo: LiveData<Todou> = liveData(Dispatchers.IO + coroutineExceptionHandler) {
        val retrivedTodo = repository.myflow
        retrivedTodo.collect {
            emit(it)
        }

    }

}