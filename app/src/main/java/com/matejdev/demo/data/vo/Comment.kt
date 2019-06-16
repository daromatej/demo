package com.matejdev.demo.data.vo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.matejdev.demo.base.values.Const

/**
 * Comment value object for API response
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class Comment(
    @field:JsonProperty(Vo.Comment.ID) var id: Int = Const.Int.ID_ABSENT,
    @field:JsonProperty(Vo.Comment.POST_ID) var postId: Int = Const.Int.ID_ABSENT,
    @field:JsonProperty(Vo.Comment.NAME) var name: String = Const.String.EMPTY,
    @field:JsonProperty(Vo.Comment.EMAIL) var email: String = Const.String.EMPTY,
    @field:JsonProperty(Vo.Comment.BODY) var body: String = Const.String.EMPTY
)
