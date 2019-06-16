package com.matejdev.demo.data.mapper

import com.matejdev.demo.data.vo.User
import com.matejdev.demo.domain.model.UserModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class UserMapperTest {

    companion object {
        const val NAME = "name"
        const val EMAIL = "email"
        const val LIST_SIZE = 3
    }

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @InjectMocks
    lateinit var mapper: UserMapper

    @Test
    fun `Test user vales are mapped to userModel`() {
        // given
        val idTag = 1
        val user = stubUser(idTag)

        // when
        val userModel = mapper.map(user)

        // then
        verifyUserModel(idTag, userModel)
    }

    @Test
    fun `Test user list is mapper to userModel list`() {
        // given
        val users = stubUserList()

        // when
        val userModels = mapper.map(users)

        // then
        verifyUserModel(userModels)
    }

    private fun verifyUserModel(idTag: Int, userModel: UserModel) {
        assertEquals("Id should be the same", idTag, userModel.id)
        assertEquals("Name should be the same", getName(idTag), userModel.name)
        assertEquals("Email should be the same", getEmail(idTag), userModel.email)
    }

    private fun verifyUserModel(userModels: List<UserModel>) {
        for (i in 0..LIST_SIZE) verifyUserModel(i, userModels[i])
    }

    private fun stubUser(idTag: Int) = User(
        id = idTag,
        name = getName(idTag),
        email = getEmail(idTag)
    )

    private fun stubUserList() = mutableListOf<User>().apply {
        for (i in 0..LIST_SIZE) add(stubUser(i))
    }

    private fun getName(idTag: Int) = "$NAME$idTag"

    private fun getEmail(idTag: Int) = "$EMAIL$idTag"
}
