package com.matejdev.demo.data.service

import com.matejdev.demo.data.vo.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Retrofit user service configuration
 */
interface UserService {
    @GET(Endpoint.USERS)
    fun getUsers(): Single<List<User>>

    @GET(Endpoint.USER)
    fun getUser(@Path("id") id: Int): Single<User>
}
