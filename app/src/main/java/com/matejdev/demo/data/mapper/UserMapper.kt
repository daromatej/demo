package com.matejdev.demo.data.mapper

import com.matejdev.demo.data.vo.User
import com.matejdev.demo.domain.model.UserModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserMapper
@Inject constructor() {

    fun map(user: User) = user.run {
        UserModel(
            id = id,
            name = name,
            email = email
        )
    }

    fun map(users: List<User>) = users.map { map(it) }
}
