package com.matejdev.demo.data.repository

import com.matejdev.demo.data.mapper.CommentMapper
import com.matejdev.demo.data.service.CommentService
import com.matejdev.demo.data.vo.Comment
import com.matejdev.demo.domain.model.CommentModel
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class CommentRepositoryImplTest {

    companion object {
        const val SOME_ID = 1
    }

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var mockCommentService: CommentService

    @Mock
    lateinit var mockCommentMapper: CommentMapper

    @Mock
    lateinit var mockCommentList: List<Comment>

    @Mock
    lateinit var mockCommentModelList: List<CommentModel>

    @Mock
    lateinit var mockComment: Comment

    @Mock
    lateinit var mockCommentModel: CommentModel

    @InjectMocks
    lateinit var testedRepository: CommentRepositoryImpl

    @Test
    fun `Should do a successful API call to getCommentsForPost`() {
        // given
        whenever(mockCommentService.getCommentsForPost(SOME_ID)).thenReturn(Single.just(mockCommentList))
        whenever(mockCommentMapper.map(mockCommentList)).thenReturn(mockCommentModelList)

        // when
        val testObserver = testedRepository.getCommentsForPost(SOME_ID).test()

        // then
        testObserver.assertComplete()
        testObserver.assertValue(mockCommentModelList)
    }

    @Test
    fun `Should do a successful API call to getComment`() {
        whenever(mockCommentService.getComment(SOME_ID)).thenReturn(Single.just(mockComment))
        whenever(mockCommentMapper.map(mockComment)).thenReturn(mockCommentModel)

        // when
        val testObserver = testedRepository.getComment(SOME_ID).test()

        // then
        testObserver.assertComplete()
        testObserver.assertValue(mockCommentModel)
    }

    @Test
    fun `Should do a successful API call to getComments`() {
        // given
        whenever(mockCommentService.getComments()).thenReturn(Single.just(mockCommentList))
        whenever(mockCommentMapper.map(mockCommentList)).thenReturn(mockCommentModelList)

        // when
        val testObserver = testedRepository.getComments().test()

        // then
        testObserver.assertComplete()
        testObserver.assertValue(mockCommentModelList)
    }
}
