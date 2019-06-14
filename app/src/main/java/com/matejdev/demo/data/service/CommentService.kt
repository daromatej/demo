package com.matejdev.demo.data.service

import com.matejdev.demo.data.vo.Comment
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CommentService {

    @GET(Endpoint.COMMENTS)
    fun getCommentsForPost(@Query("postId") postId: Int): Call<List<Comment>>

    @GET(Endpoint.COMMENTS)
    fun getComments(): Call<List<Comment>>

    @GET(Endpoint.COMMENT)
    fun getComment(@Path("id") id: Int): Call<Comment>
}
