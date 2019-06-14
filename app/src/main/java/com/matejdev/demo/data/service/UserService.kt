package com.matejdev.demo.data.service

import com.matejdev.demo.data.vo.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET(Endpoint.USERS)
    fun getUsers(): Call<List<User>>

    @GET(Endpoint.USER)
    fun getUser(@Path("id") id: Int): Call<User>
}
