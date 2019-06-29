package com.matejdev.demo.data.vo

import com.google.gson.annotations.SerializedName
import com.matejdev.demo.base.values.Const

/**
 * Comment value object for API response
 */
data class Comment(
    @field:SerializedName(Vo.Comment.ID) var id: Int = Const.Int.ID_ABSENT,
    @field:SerializedName(Vo.Comment.POST_ID) var postId: Int = Const.Int.ID_ABSENT,
    @field:SerializedName(Vo.Comment.NAME) var name: String = Const.String.EMPTY,
    @field:SerializedName(Vo.Comment.EMAIL) var email: String = Const.String.EMPTY,
    @field:SerializedName(Vo.Comment.BODY) var body: String = Const.String.EMPTY
)
