package com.matejdev.demo.a.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.matejdev.demo.a.domain.usecase.GetToDoCountUseCase
import com.matejdev.demo.base.util.LogUtil
import io.reactivex.observers.DisposableSingleObserver


class ToDoCountViewModel : ViewModel() {

    private val toDoCount: MutableLiveData<String> by lazy {
        MutableLiveData<String>().also { loadToDoCount() }
    }

    private val todoCountUseCase = GetToDoCountUseCase()

    fun getToDoCount(): LiveData<String> {
        return toDoCount
    }

    private fun loadToDoCount() {
        todoCountUseCase.execute(CountObserver())
    }

    override fun onCleared() {
        super.onCleared()
        todoCountUseCase.dispose()
    }

    inner class CountObserver: DisposableSingleObserver<String>(){
        override fun onSuccess(t: String) {
            LogUtil.logSuccess(t)
            toDoCount.value = t
        }

        override fun onError(e: Throwable) {
            toDoCount.value = "error"
            LogUtil.logFailure(e.toString())
        }
    }
}
