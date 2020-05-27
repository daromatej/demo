package com.matejdev.demo.a.data.repository

import com.matejdev.demo.a.data.mapper.ToDoMapper
import com.matejdev.demo.a.data.service.RetrofitInstance.endpoint
import com.matejdev.demo.a.data.service.ToDoService
import com.matejdev.demo.a.domain.model.ToDoModel
import com.matejdev.demo.a.domain.repository.ToDoRepository
import io.reactivex.Single

class ToDoRepositoryImpl : ToDoRepository {

    private val toDoService by lazy { endpoint.create(ToDoService::class.java) }

    override fun getToDoList(): Single<List<ToDoModel>> = toDoService.getToDoList()
        .map { ToDoMapper.map(it) }
}
