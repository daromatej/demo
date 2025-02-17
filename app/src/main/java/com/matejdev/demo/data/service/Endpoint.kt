package com.matejdev.demo.data.service

/**
 * Endpoint path definitions for services
 */
interface Endpoint {
    companion object{
        const val POSTS = "posts"
        const val POST = "posts/{id}"

        const val USERS = "users"
        const val USER = "users/{id}"

        const val COMMENTS = "comments"
        const val COMMENT = "comments/{id}"
    }
}
