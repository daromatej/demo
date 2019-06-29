package com.matejdev.demo.data.vo

import com.google.gson.annotations.SerializedName
import com.matejdev.demo.base.values.Const

/**
 * Post value object for API response
 */
data class Post(
    @field:SerializedName(Vo.Post.ID) var id: Int = Const.Int.ID_ABSENT,
    @field:SerializedName(Vo.Post.USER_ID) var userId: Int = Const.Int.ID_ABSENT,
    @field:SerializedName(Vo.Post.TITLE) var title: String = Const.String.EMPTY,
    @field:SerializedName(Vo.Post.BODY) var body: String = Const.String.EMPTY
)


