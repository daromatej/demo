package com.matejdev.demo.data.repository

import com.matejdev.demo.base.util.LogUtil
import com.matejdev.demo.data.mapper.UserMapper
import com.matejdev.demo.data.service.UserService
import com.matejdev.demo.domain.model.UserModel
import com.matejdev.demo.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Repository for fetching users
 */
class UserRepositoryImpl
@Inject constructor(
    private val service: UserService,
    private val mapper: UserMapper
) : UserRepository {

    override fun getUser(id: Int): Single<UserModel> = Single
        .fromCallable { service.getUser(id).execute().body() }
        .map { mapper.map(it) }
        .doOnSuccess { LogUtil.logSuccess(it.id) }
        .doOnError { LogUtil.logFailure() }

    override fun getUsers() = Single
        .fromCallable { service.getUsers().execute().body() }
        .map { mapper.map(it) }
        .doOnSuccess { LogUtil.logSuccess(it.size) }
        .doOnError { LogUtil.logFailure() }
}
