package com.matejdev.demo.data.mapper

import com.matejdev.demo.data.vo.Comment
import com.matejdev.demo.domain.model.CommentModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Data layer [Comment] to domain layer [CommentModel] mapper
 */
@Singleton
class CommentMapper
@Inject constructor() {

    fun map(comment: Comment) = comment.run {
        CommentModel(
            id = id,
            postId = postId,
            name = name,
            email = email,
            body = body
        )
    }

    fun map(comments: List<Comment>) = comments.map { map(it) }
}
