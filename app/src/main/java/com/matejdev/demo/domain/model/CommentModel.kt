package com.matejdev.demo.domain.model

import java.io.Serializable

data class CommentModel(
    val id: Int,
    val postId: Int,
    val name: String,
    val email: String,
    val body: String
) : Serializable
