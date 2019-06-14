package com.matejdev.demo.domain.repository

import com.matejdev.demo.domain.model.CommentModel
import io.reactivex.Single

interface CommentRepository {

    fun getComment(id: Int): Single<CommentModel>

    fun getComments(): Single<List<CommentModel>>

    fun getCommentsForPost(postId: Int): Single<List<CommentModel>>
}
