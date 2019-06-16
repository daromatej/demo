package com.matejdev.demo.domain.model

import java.io.Serializable

/**
 * Post model dedicated for domain and presentation layers
 */
data class PostModel(
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
) : Serializable
