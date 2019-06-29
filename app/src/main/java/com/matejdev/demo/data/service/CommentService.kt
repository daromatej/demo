package com.matejdev.demo.data.service

import com.matejdev.demo.data.vo.Comment
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit comment service configuration
 */
interface CommentService {

    @GET(Endpoint.COMMENTS)
    fun getCommentsForPost(@Query("postId") postId: Int): Single<List<Comment>>

    @GET(Endpoint.COMMENTS)
    fun getComments(): Single<List<Comment>>

    @GET(Endpoint.COMMENT)
    fun getComment(@Path("id") id: Int): Single<Comment>
}
