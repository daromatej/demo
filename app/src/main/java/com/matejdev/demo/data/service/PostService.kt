package com.matejdev.demo.data.service

import com.matejdev.demo.data.vo.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostService {

    @GET(Endpoint.POSTS)
    fun getPostsForUser(@Query("userId") userId: Int): Call<List<Post>>

    @GET(Endpoint.POSTS)
    fun getPosts(): Call<List<Post>>

    @GET(Endpoint.POST)
    fun getPost(@Path("id") id: Int): Call<Post>
}
