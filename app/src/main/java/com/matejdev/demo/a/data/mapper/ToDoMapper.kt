package com.matejdev.demo.a.data.mapper

import com.matejdev.demo.a.data.model.ToDo
import com.matejdev.demo.a.domain.model.ToDoModel

object ToDoMapper {

    fun map(toDo: ToDo) = toDo.let {
        ToDoModel(
            userId = it.userId,
            id = it.id,
            title = it.title,
            completed = it.completed
        )
    }

    fun map(toDoList: List<ToDo>) = toDoList.map { map(it) }
}
