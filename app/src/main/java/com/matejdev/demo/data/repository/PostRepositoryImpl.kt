package com.matejdev.demo.data.repository

import com.matejdev.demo.base.util.LogUtil
import com.matejdev.demo.data.mapper.PostMapper
import com.matejdev.demo.data.service.PostService
import com.matejdev.demo.domain.model.PostModel
import com.matejdev.demo.domain.repository.PostRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Repository for fetching posts
 */
class PostRepositoryImpl
@Inject constructor(
    private val service: PostService,
    private val mapper: PostMapper
) : PostRepository {

    override fun getPostsForUser(userId: Int): Single<List<PostModel>> = Single
        .fromCallable { service.getPostsForUser(userId).execute().body() }
        .map { mapper.map(it) }
        .doOnSuccess { LogUtil.logSuccess(it.size) }
        .doOnError { LogUtil.logFailure() }

    override fun getPost(id: Int): Single<PostModel> = Single
        .fromCallable { service.getPost(id).execute().body() }
        .map { mapper.map(it) }
        .doOnSuccess { LogUtil.logSuccess(it.id) }
        .doOnError { LogUtil.logFailure() }

    override fun getPosts() = Single
        .fromCallable { service.getPosts().execute().body() }
        .map { mapper.map(it) }
        .doOnSuccess { LogUtil.logSuccess(it.size) }
        .doOnError { LogUtil.logFailure() }
}
