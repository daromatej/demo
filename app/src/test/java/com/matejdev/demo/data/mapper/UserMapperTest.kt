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
        val user = createUser(idTag)

        // when
        val userModel = mapper.map(user)

        // then
        assertEquals(createUserModel(idTag), userModel)
    }

    @Test
    fun `Test user list is mapper to userModel list`() {
        // given
        val users = createUserList()

        // when
        val userModels = mapper.map(users)

        // then
        assertEquals(createUserModelList(), userModels)
    }

    private fun createUser(idTag: Int) = User(
        id = idTag,
        name = getName(idTag),
        email = getEmail(idTag)
    )

    private fun createUserModel(idTag: Int) = UserModel(
        id = idTag,
        name = getName(idTag),
        email = getEmail(idTag)
    )

    private fun createUserList() = mutableListOf<User>().apply {
        for (i in 0..LIST_SIZE) add(createUser(i))
    }

    private fun createUserModelList() = mutableListOf<UserModel>().apply {
        for (i in 0..LIST_SIZE) add(createUserModel(i))
    }

    private fun getName(idTag: Int) = "$NAME$idTag"

    private fun getEmail(idTag: Int) = "$EMAIL$idTag"
}
