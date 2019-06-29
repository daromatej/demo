package com.matejdev.demo.data.repository

import com.matejdev.demo.data.mapper.UserMapper
import com.matejdev.demo.data.service.UserService
import com.matejdev.demo.data.vo.User
import com.matejdev.demo.domain.model.UserModel
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class UserRepositoryImplTest {

    companion object {
        const val SOME_ID = 1
    }

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var mockUserService: UserService

    @Mock
    lateinit var mockUserMapper: UserMapper

    @Mock
    lateinit var mockUserList: List<User>

    @Mock
    lateinit var mockUserModelList: List<UserModel>

    @Mock
    lateinit var mockUser: User

    @Mock
    lateinit var mockUserModel: UserModel

    @InjectMocks
    lateinit var testedRepository: UserRepositoryImpl

    @Test
    fun `Should do a successful API call to getUser`() {
        whenever(mockUserService.getUser(SOME_ID)).thenReturn(Single.just(mockUser))
        whenever(mockUserMapper.map(mockUser)).thenReturn(mockUserModel)

        // when
        val testObserver = testedRepository.getUser(SOME_ID).test()

        // then
        testObserver.assertComplete()
        testObserver.assertValue(mockUserModel)
    }

    @Test
    fun `Should do a successful API call to getUsers`() {
        // given
        whenever(mockUserService.getUsers()).thenReturn(Single.just(mockUserList))
        whenever(mockUserMapper.map(mockUserList)).thenReturn(mockUserModelList)

        // when
        val testObserver = testedRepository.getUsers().test()

        // then
        testObserver.assertComplete()
        testObserver.assertValue(mockUserModelList)
    }
}
