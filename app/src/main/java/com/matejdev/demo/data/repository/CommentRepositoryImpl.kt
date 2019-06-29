package com.matejdev.demo.data.repository

import com.matejdev.demo.base.util.LogUtil
import com.matejdev.demo.data.mapper.CommentMapper
import com.matejdev.demo.data.service.CommentService
import com.matejdev.demo.domain.repository.CommentRepository
import javax.inject.Inject

/**
 * Repository for fetching comments
 */
class CommentRepositoryImpl
@Inject constructor(
    private val service: CommentService,
    private val mapper: CommentMapper
) : CommentRepository {

    override fun getCommentsForPost(postId: Int) = service.getCommentsForPost(postId)
        .map { mapper.map(it) }
        .doOnSuccess { LogUtil.logSuccess(it.size) }
        .doOnError { LogUtil.logFailure() }

    override fun getComment(id: Int) = service.getComment(id)
        .map { mapper.map(it) }
        .doOnSuccess { LogUtil.logSuccess(it.id) }
        .doOnError { LogUtil.logFailure() }

    override fun getComments() = service.getComments()
        .map { mapper.map(it) }
        .doOnSuccess { LogUtil.logSuccess(it.size) }
        .doOnError { LogUtil.logFailure() }
}
