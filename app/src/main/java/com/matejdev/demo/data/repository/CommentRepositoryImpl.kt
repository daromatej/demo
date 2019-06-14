package com.matejdev.demo.data.repository

import com.matejdev.demo.base.util.LogUtil
import com.matejdev.demo.data.mapper.CommentMapper
import com.matejdev.demo.data.service.CommentService
import com.matejdev.demo.domain.model.CommentModel
import com.matejdev.demo.domain.repository.CommentRepository
import io.reactivex.Single
import javax.inject.Inject

class CommentRepositoryImpl
@Inject constructor(
    private val service: CommentService,
    private val mapper: CommentMapper
) : CommentRepository {

    override fun getCommentsForPost(postId: Int) = Single
        .fromCallable { service.getCommentsForPost(postId).execute().body() }
        .map { mapper.map(it) }
        .doOnSuccess { LogUtil.logSuccess(it.size) }
        .doOnError { LogUtil.logFailure() }

    override fun getComment(id: Int): Single<CommentModel> = Single
        .fromCallable { service.getComment(id).execute().body() }
        .map { mapper.map(it) }
        .doOnSuccess { LogUtil.logSuccess(it.id) }
        .doOnError { LogUtil.logFailure() }

    override fun getComments() = Single
        .fromCallable { service.getComments().execute().body() }
        .map { mapper.map(it) }
        .doOnSuccess { LogUtil.logSuccess(it.size) }
        .doOnError { LogUtil.logFailure() }
}
