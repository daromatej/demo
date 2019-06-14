package com.matejdev.demo.data.vo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.matejdev.demo.base.values.Const

@JsonIgnoreProperties(ignoreUnknown = true)
data class Post(
    @field:JsonProperty(Vo.Post.ID) var id: Int = Const.Int.ID_ABSENT,
    @field:JsonProperty(Vo.Post.USER_ID) var userId: Int = Const.Int.ID_ABSENT,
    @field:JsonProperty(Vo.Post.TITLE) var title: String = Const.String.EMPTY,
    @field:JsonProperty(Vo.Post.BODY) var body: String = Const.String.EMPTY
)


