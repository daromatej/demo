package com.matejdev.demo.domain.repository

import com.matejdev.demo.domain.model.UserModel
import io.reactivex.Single

interface UserRepository {

    fun getUser(id: Int): Single<UserModel>

    fun getUsers(): Single<List<UserModel>>
}
