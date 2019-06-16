package com.matejdev.demo.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.matejdev.demo.domain.model.CommentModel
import com.matejdev.demo.domain.usecase.GetCommentsUseCase
import com.matejdev.demo.presentation.viewmodel.CommentListViewModel.CommentsObserver
import com.nhaarman.mockito_kotlin.KArgumentCaptor
import com.nhaarman.mockito_kotlin.any
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

class CommentListViewModelTest {

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
    lateinit var mockCommentsUseCase: GetCommentsUseCase

    @Mock
    lateinit var mockThrowable: Throwable

    @Mock
    lateinit var mockCommentModel1: CommentModel

    @Mock
    lateinit var mockCommentModel2: CommentModel

    @Mock
    lateinit var mockCommentModel3: CommentModel

    @InjectMocks
    lateinit var testedModel: CommentListViewModel

    @Test
    fun `Should execute GetCommentsUseCase on loadPosts`() {
        // when
        testedModel.loadComments(SOME_ID)

        // then
        verify(mockCommentsUseCase).execute(any<CommentsObserver>(), eq(SOME_ID))
        assertTrue("Should show loader", testedModel.state.value?.showLoader!!)
        assertNull("Should not change error state", testedModel.state.value?.showError)
    }

    @Test
    fun `Should hide loader and show error on use case error`() {
        // given
        val observer = captureObserverInLoadComments()

        // when
        observer.firstValue.onError(mockThrowable)

        // then
        assertFalse("Should hide loader", testedModel.state.value?.showLoader!!)
        assertTrue("Should show error", testedModel.state.value?.showError!!)
    }

    @Test
    fun `Should hide loader and update users on success`() {
        // given
        val observer = captureObserverInLoadComments()
        val comments = getStubbedCommentList()
        // when
        observer.firstValue.onSuccess(comments)

        // then
        assertFalse("Should hide loader", testedModel.state.value?.showLoader!!)
        assertNull("Should not change error state", testedModel.state.value?.showError)
        assertEquals("Both list items should be the same", comments, testedModel.comments.value)
    }

    private fun captureObserverInLoadComments(): KArgumentCaptor<CommentsObserver> {
        val observer = argumentCaptor<CommentsObserver>()
        testedModel.loadComments(SOME_ID)
        verify(mockCommentsUseCase).execute(observer.capture(), eq(SOME_ID))

        return observer
    }

    private fun getStubbedCommentList() = listOf(
        mockCommentModel1,
        mockCommentModel2,
        mockCommentModel3
    )
}
