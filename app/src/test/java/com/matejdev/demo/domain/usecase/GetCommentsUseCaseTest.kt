package com.matejdev.demo.domain.usecase

import com.matejdev.demo.base.rx.RxSchedulersFactory
import com.matejdev.demo.domain.model.CommentModel
import com.matejdev.demo.domain.repository.CommentRepository
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class GetCommentsUseCaseTest {

    companion object {
        const val SOME_ID = 1
    }

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var mockRxSchedulersFactory: RxSchedulersFactory

    @Mock
    lateinit var mockCommentRepository: CommentRepository

    @Mock
    lateinit var mockCommentList: List<CommentModel>

    @InjectMocks
    lateinit var testedUseCase: GetCommentsUseCase

    @Test
    fun `Should return error when called with null id`() {
        // when
        val testObserver = testedUseCase.useCase(null).test()

        // then
        testObserver.assertError(IllegalArgumentException::class.java)
    }

    @Test
    fun `Should return list of comments when called with some id`() {
        // given
        whenever(mockCommentRepository.getCommentsForPost(SOME_ID)).thenReturn(Single.just(mockCommentList))

        // when
        val testObserver = testedUseCase.useCase(SOME_ID).test()

        // then
        testObserver.assertComplete()
        testObserver.assertValue(mockCommentList)
    }
}
