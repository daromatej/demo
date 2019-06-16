package com.matejdev.demo.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.matejdev.demo.domain.model.PostModel
import com.matejdev.demo.domain.usecase.GetPostsUseCase
import com.matejdev.demo.presentation.viewmodel.PostListViewModel.*
import com.nhaarman.mockito_kotlin.KArgumentCaptor
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.anyOrNull
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
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

class PostListViewModelTest {

    companion object {
        const val SOME_ID = 1
    }

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Rule
    @JvmField
    val executorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockGetPostsUseCase: GetPostsUseCase

    @Mock
    lateinit var mockThrowable: Throwable

    @Mock
    lateinit var mockPostModel1: PostModel

    @Mock
    lateinit var mockPostModel2: PostModel

    @Mock
    lateinit var mockPostModel3: PostModel

    @InjectMocks
    lateinit var testedModel: PostListViewModel

    @Test
    fun `Should execute GetPostsUseCase on loadPosts`() {
        // when
        testedModel.loadPosts(SOME_ID)

        // then
        verify(mockGetPostsUseCase).execute(any<PostsObserver>(), eq(SOME_ID))
        assertTrue("Should show loader", testedModel.state.value?.showLoader!!)
        assertNull("Should not change error state", testedModel.state.value?.showError)
    }

    @Test
    fun `Should hide loader and show error on use case error`() {
        // given
        val observer = captureObserverInLoadPosts()

        // when
        observer.firstValue.onError(mockThrowable)

        // then
        assertFalse("Should hide loader", testedModel.state.value?.showLoader!!)
        assertTrue("Should show error", testedModel.state.value?.showError!!)
    }

    @Test
    fun `Should hide loader and update users on success`() {
        // given
        val observer = captureObserverInLoadPosts()
        val posts = getStubbedPostList()
        // when
        observer.firstValue.onSuccess(posts)

        // then
        assertFalse("Should hide loader", testedModel.state.value?.showLoader!!)
        assertNull("Should not change error state", testedModel.state.value?.showError)
        assertEquals("Both list items should be the same", posts, testedModel.posts.value)
    }

    private fun captureObserverInLoadPosts(): KArgumentCaptor<PostsObserver> {
        val observer = argumentCaptor<PostsObserver>()
        testedModel.loadPosts(SOME_ID)
        verify(mockGetPostsUseCase).execute(observer.capture(), eq(SOME_ID))

        return observer
    }

    private fun getStubbedPostList() = listOf(
        mockPostModel1,
        mockPostModel2,
        mockPostModel3
    )
}
