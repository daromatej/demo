package com.matejdev.demo.a.domain.usecase

import com.matejdev.demo.a.data.repository.ToDoRepositoryImpl
import com.matejdev.demo.a.domain.repository.ToDoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class GetToDoCountUseCase {

    private val toDoRepository: ToDoRepository by lazy { ToDoRepositoryImpl() }

    private val disposables = CompositeDisposable()

    fun execute(observer: DisposableSingleObserver<String>){

        toDoRepository.getToDoList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.count().toString() }
            .subscribeWith(observer)
            .let { disposables.add(it) }
    }

    fun dispose() = disposables.dispose()
}
