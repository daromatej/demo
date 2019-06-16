package com.matejdev.demo.domain.usecase

import com.matejdev.demo.base.rx.RxSchedulersFactory
import com.matejdev.demo.domain.model.UserModel
import com.matejdev.demo.domain.repository.UserRepository
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class GetUsersUseCaseTest {

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var mockRxSchedulersFactory: RxSchedulersFactory

    @Mock
    lateinit var mockUserRepository: UserRepository

    @Mock
    lateinit var mockUserList: List<UserModel>

    @InjectMocks
    lateinit var testedUseCase: GetUsersUseCase

    @Test
    fun `Should return list of users`() {
        // given
        whenever(mockUserRepository.getUsers()).thenReturn(Single.just(mockUserList))

        // when
        val testObserver = testedUseCase.useCase(null).test()

        // then
        testObserver.assertComplete()
        testObserver.assertValue(mockUserList)
    }
}
