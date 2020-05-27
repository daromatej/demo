package com.matejdev.demo.a.data.service

import com.matejdev.demo.a.data.model.ToDo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ToDoService {

    @GET("/todos")
    fun getToDoList(): Single<List<ToDo>>

    @GET("/todos/{id}")
    fun getToDo(@Path("id") id: Int): Single<ToDo>

//    @GET(Endpoint.POSTS)
//    fun getPostsForUser(@Path("id") id: Int): Single<List<Post>>
}
