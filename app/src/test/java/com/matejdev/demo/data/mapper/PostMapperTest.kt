package com.matejdev.demo.data.mapper

import com.matejdev.demo.data.vo.Post
import com.matejdev.demo.domain.model.PostModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class PostMapperTest {

    companion object {
        const val TITLE = "title"
        const val BODY = "body"
        const val LIST_SIZE = 3
    }

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @InjectMocks
    lateinit var mapper: PostMapper

    @Test
    fun `Test post vales are mapped to postModel`() {
        // given
        val idTag = 1
        val post = createPost(idTag)

        // when
        val postModel = mapper.map(post)

        // then
        assertEquals(createPostModel(idTag), postModel)
    }

    @Test
    fun `Test post list is mapper to postModel list`() {
        // given
        val posts = createPostList()

        // when
        val postModels = mapper.map(posts)

        // then
        assertEquals(createPostModelList(), postModels)
    }

    private fun createPost(idTag: Int) = Post(
        id = idTag,
        userId = idTag,
        title = getTitle(idTag),
        body = getBody(idTag)
    )

    private fun createPostModel(idTag: Int) = PostModel(
        id = idTag,
        userId = idTag,
        title = getTitle(idTag),
        body = getBody(idTag)
    )

    private fun createPostList() = mutableListOf<Post>().apply {
        for (i in 0..LIST_SIZE) add(createPost(i))
    }

    private fun createPostModelList() = mutableListOf<PostModel>().apply {
        for (i in 0..LIST_SIZE) add(createPostModel(i))
    }

    private fun getTitle(idTag: Int) = "$TITLE$idTag"

    private fun getBody(idTag: Int) = "$BODY$idTag"
}
