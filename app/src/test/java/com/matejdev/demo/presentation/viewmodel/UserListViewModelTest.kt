package com.matejdev.demo.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.matejdev.demo.domain.model.UserModel
import com.matejdev.demo.domain.usecase.GetUsersUseCase
import com.matejdev.demo.presentation.viewmodel.UserListViewModel.UsersObserver
import com.nhaarman.mockito_kotlin.KArgumentCaptor
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.isNull
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class UserListViewModelTest {

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Rule
    @JvmField
    val executorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockGetUsersUseCase: GetUsersUseCase

    @Mock
    lateinit var mockThrowable: Throwable

    @Mock
    lateinit var mockUserModel1: UserModel

    @Mock
    lateinit var mockUserModel2: UserModel

    @Mock
    lateinit var mockUserModel3: UserModel

    @InjectMocks
    lateinit var testedModel: UserListViewModel

    @Test
    fun `Should execute GetUsersUseCase on init`() {
        // when
        testedModel.init()

        // then
        verify(mockGetUsersUseCase).execute(any<UsersObserver>(), isNull())
        assertTrue("Should show loader", testedModel.state.value?.showLoader!!)
        assertNull("Should not change error state", testedModel.state.value?.showError)
    }

    @Test
    fun `Should hide loader and show error on use case error`() {
        // given
        val observer = captureObserverInInit()

        // when
        observer.firstValue.onError(mockThrowable)

        // then
        assertFalse("Should hide loader", testedModel.state.value?.showLoader!!)
        assertTrue("Should show error", testedModel.state.value?.showError!!)
    }

    @Test
    fun `Should hide loader and update users on success`() {
        // given
        val observer = captureObserverInInit()
        val users = getStubbedUserList()
        // when
        observer.firstValue.onSuccess(users)

        // then
        assertFalse("Should hide loader", testedModel.state.value?.showLoader!!)
        assertNull("Should not change error state", testedModel.state.value?.showError)
        assertEquals("Both list items should be the same", users, testedModel.users.value)
    }

    private fun captureObserverInInit(): KArgumentCaptor<UsersObserver> {
        val observer = argumentCaptor<UsersObserver>()
        testedModel.init()
        verify(mockGetUsersUseCase).execute(observer.capture(), isNull())

        return observer
    }

    private fun getStubbedUserList() = listOf(
        mockUserModel1,
        mockUserModel2,
        mockUserModel3
    )
}
