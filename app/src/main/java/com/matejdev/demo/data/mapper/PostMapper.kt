package com.matejdev.demo.data.mapper

import com.matejdev.demo.data.vo.Post
import com.matejdev.demo.domain.model.PostModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostMapper
@Inject constructor() {

    fun map(post: Post) = post.run {
        PostModel(
            id = id,
            userId = userId,
            title = title,
            body = body
        )
    }

    fun map(posts: List<Post>) = posts.map { map(it) }
}
