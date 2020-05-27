package com.matejdev.demo.a.domain.repository

import com.matejdev.demo.a.domain.model.ToDoModel
import io.reactivex.Single

interface ToDoRepository {

    fun getToDoList(): Single<List<ToDoModel>>

}
