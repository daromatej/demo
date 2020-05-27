package com.matejdev.demo.a.data.model

import com.google.gson.annotations.SerializedName

data class ToDo(
    @field:SerializedName("userId") var userId: Int = -1,
    @field:SerializedName("id") var id: Int = -1,
    @field:SerializedName("title") var title: String = "",
    @field:SerializedName("completed") var completed: Boolean = false
)
