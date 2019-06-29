package com.matejdev.demo.data.service

import com.matejdev.demo.data.vo.Post
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit post service configuration
 */
interface PostService {

    @GET(Endpoint.POSTS)
    fun getPostsForUser(@Query("userId") userId: Int): Single<List<Post>>

    @GET(Endpoint.POSTS)
    fun getPosts(): Single<List<Post>>

    @GET(Endpoint.POST)
    fun getPost(@Path("id") id: Int): Single<Post>
}
