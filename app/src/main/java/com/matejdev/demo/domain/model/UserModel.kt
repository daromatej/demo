package com.matejdev.demo.domain.model

import java.io.Serializable

data class UserModel(
    val id: Int,
    val name: String,
    val email: String
) : Serializable
