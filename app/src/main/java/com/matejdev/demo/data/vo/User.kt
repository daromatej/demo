package com.matejdev.demo.data.vo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.matejdev.demo.base.values.Const

@JsonIgnoreProperties(ignoreUnknown = true)
data class User(
    @field:JsonProperty(Vo.User.ID) var id: Int = Const.Int.ID_ABSENT,
    @field:JsonProperty(Vo.User.NAME) var name: String = Const.String.EMPTY,
    @field:JsonProperty(Vo.User.EMAIL) var email: String = Const.String.EMPTY
)
