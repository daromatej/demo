package com.matejdev.demo.data.vo

import com.google.gson.annotations.SerializedName
import com.matejdev.demo.base.values.Const

/**
 * User value object for API response
 */
data class User(
    @field:SerializedName(Vo.User.ID) var id: Int = Const.Int.ID_ABSENT,
    @field:SerializedName(Vo.User.NAME) var name: String = Const.String.EMPTY,
    @field:SerializedName(Vo.User.EMAIL) var email: String = Const.String.EMPTY
)
