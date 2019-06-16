package com.matejdev.demo.domain.model

import java.io.Serializable

/**
 * Comment model dedicated for domain and presentation layers
 */
data class CommentModel(
    val id: Int,
    val postId: Int,
    val name: String,
    val email: String,
    val body: String
) : Serializable
