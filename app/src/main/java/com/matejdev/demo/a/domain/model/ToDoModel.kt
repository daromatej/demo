package com.matejdev.demo.a.domain.model

import java.io.Serializable

data class ToDoModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
) : Serializable
