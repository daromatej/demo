package com.matejdev.demo.domain.repository

import com.matejdev.demo.domain.model.PostModel
import io.reactivex.Single

interface PostRepository {

    fun getPostsForUser(userId: Int): Single<List<PostModel>>

    fun getPost(id: Int): Single<PostModel>

    fun getPosts(): Single<List<PostModel>>
}
