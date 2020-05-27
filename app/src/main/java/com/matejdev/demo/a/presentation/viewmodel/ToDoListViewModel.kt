package com.matejdev.demo.a.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.matejdev.demo.a.domain.model.ToDoModel
import com.matejdev.demo.a.domain.usecase.GetToDoListUseCase
import com.matejdev.demo.base.util.LogUtil
import io.reactivex.observers.DisposableSingleObserver

class ToDoListViewModel : ViewModel() {

    private val toDoList: MutableLiveData<List<ToDoModel>> by lazy {
        MutableLiveData<List<ToDoModel>>().also { loadToDoList() }
    }

    private val todoListUseCase = GetToDoListUseCase()

    fun getToDoList(): LiveData<List<ToDoModel>> {
        return toDoList
    }

    private fun loadToDoList() {
        todoListUseCase.execute(CountObserver())
    }

    override fun onCleared() {
        super.onCleared()
        todoListUseCase.dispose()
    }

    inner class CountObserver: DisposableSingleObserver<List<ToDoModel>>(){
        override fun onSuccess(t: List<ToDoModel>) {
            LogUtil.logSuccess(t.toString())
            toDoList.value = t
        }

        override fun onError(e: Throwable) {
            LogUtil.logFailure(e.toString())
        }
    }
}
