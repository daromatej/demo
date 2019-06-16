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
        val post = stubPost(idTag)

        // when
        val postModel = mapper.map(post)

        // then
        verifyPostModel(idTag, postModel)
    }

    @Test
    fun `Test post list is mapper to postModel list`() {
        // given
        val posts = stubPostList()

        // when
        val postModels = mapper.map(posts)

        // then
        verifyPostModel(postModels)
    }

    private fun verifyPostModel(idTag: Int, postModel: PostModel) {
        assertEquals("Id should be the same", idTag, postModel.id)
        assertEquals("UserId should be the same", idTag, postModel.userId)
        assertEquals("Title should be the same", getTitle(idTag), postModel.title)
        assertEquals("Body should be the same", getBody(idTag), postModel.body)
    }

    private fun verifyPostModel(postModels: List<PostModel>) {
        for (i in 0..LIST_SIZE) verifyPostModel(i, postModels[i])
    }

    private fun stubPost(idTag: Int) = Post(
        id = idTag,
        userId = idTag,
        title = getTitle(idTag),
        body = getBody(idTag)
    )

    private fun stubPostList() = mutableListOf<Post>().apply {
        for (i in 0..LIST_SIZE) add(stubPost(i))
    }

    private fun getTitle(idTag: Int) = "$TITLE$idTag"

    private fun getBody(idTag: Int) = "$BODY$idTag"
}
