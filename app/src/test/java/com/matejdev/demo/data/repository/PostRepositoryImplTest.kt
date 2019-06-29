package com.matejdev.demo.data.repository

import com.matejdev.demo.data.mapper.PostMapper
import com.matejdev.demo.data.service.PostService
import com.matejdev.demo.data.vo.Post
import com.matejdev.demo.domain.model.PostModel
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class PostRepositoryImplTest {

    companion object {
        const val SOME_ID = 1
    }

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var mockPostService: PostService

    @Mock
    lateinit var mockPostMapper: PostMapper

    @Mock
    lateinit var mockPostList: List<Post>

    @Mock
    lateinit var mockPostModelList: List<PostModel>

    @Mock
    lateinit var mockPost: Post

    @Mock
    lateinit var mockPostModel: PostModel

    @InjectMocks
    lateinit var testedRepository: PostRepositoryImpl

    @Test
    fun `Should do a successful API call to getPostsForUser`() {
        // given
        whenever(mockPostService.getPostsForUser(SOME_ID)).thenReturn(Single.just(mockPostList))
        whenever(mockPostMapper.map(mockPostList)).thenReturn(mockPostModelList)

        // when
        val testObserver = testedRepository.getPostsForUser(SOME_ID).test()

        // then
        testObserver.assertComplete()
        testObserver.assertValue(mockPostModelList)
    }

    @Test
    fun `Should do a successful API call to getPost`() {
        whenever(mockPostService.getPost(SOME_ID)).thenReturn(Single.just(mockPost))
        whenever(mockPostMapper.map(mockPost)).thenReturn(mockPostModel)

        // when
        val testObserver = testedRepository.getPost(SOME_ID).test()

        // then
        testObserver.assertComplete()
        testObserver.assertValue(mockPostModel)
    }

    @Test
    fun `Should do a successful API call to getPosts`() {
        // given
        whenever(mockPostService.getPosts()).thenReturn(Single.just(mockPostList))
        whenever(mockPostMapper.map(mockPostList)).thenReturn(mockPostModelList)

        // when
        val testObserver = testedRepository.getPosts().test()

        // then
        testObserver.assertComplete()
        testObserver.assertValue(mockPostModelList)
    }
}
