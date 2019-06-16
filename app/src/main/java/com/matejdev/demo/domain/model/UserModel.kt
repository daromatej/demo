package com.matejdev.demo.domain.model

import java.io.Serializable

/**
 * User model dedicated for domain and presentation layers
 */
data class UserModel(
    val id: Int,
    val name: String,
    val email: String
) : Serializable
