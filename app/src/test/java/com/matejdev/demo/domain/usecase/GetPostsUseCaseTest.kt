package com.matejdev.demo.domain.usecase

import com.matejdev.demo.base.rx.RxSchedulersFactory
import com.matejdev.demo.domain.model.PostModel
import com.matejdev.demo.domain.repository.PostRepository
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class GetPostsUseCaseTest {

    companion object {
        const val SOME_ID = 1
    }

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var mockRxSchedulersFactory: RxSchedulersFactory

    @Mock
    lateinit var mockPostRepository: PostRepository

    @Mock
    lateinit var mockPostList: List<PostModel>

    @InjectMocks
    lateinit var testedUseCase: GetPostsUseCase

    @Test
    fun `Should return error when called with null id`() {
        // when
        val testObserver = testedUseCase.useCase(null).test()

        // then
        testObserver.assertError(IllegalArgumentException::class.java)
    }

    @Test
    fun `Should return list of posts when called with some id`() {
        // given
        whenever(mockPostRepository.getPostsForUser(SOME_ID)).thenReturn(Single.just(mockPostList))

        // when
        val testObserver = testedUseCase.useCase(SOME_ID).test()

        // then
        testObserver.assertComplete()
        testObserver.assertValue(mockPostList)
    }
}
