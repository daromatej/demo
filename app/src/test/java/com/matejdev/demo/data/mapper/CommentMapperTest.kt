package com.matejdev.demo.data.mapper

import com.matejdev.demo.data.vo.Comment
import com.matejdev.demo.domain.model.CommentModel
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class CommentMapperTest {

    companion object {
        const val NAME = "name"
        const val EMAIL = "email"
        const val BODY = "body"
        const val LIST_SIZE = 3
    }

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @InjectMocks
    lateinit var mapper: CommentMapper

    @Test
    fun `Test comment vales are mapped to commentModel`() {
        // given
        val idTag = 1
        val comment = createComment(idTag)

        // when
        val commentModel = mapper.map(comment)

        // then
        assertEquals(createCommentModel(idTag), commentModel)
    }

    @Test
    fun `Test comment list is mapper to commentModel list`() {
        // given
        val comments = createCommentList()

        // when
        val commentModels = mapper.map(comments)

        // then
        assertEquals(createCommentModelList(), commentModels)
    }

    private fun createComment(idTag: Int) = Comment(
        id = idTag,
        postId = idTag,
        name = getName(idTag),
        email = getEmail(idTag),
        body = getBody(idTag)
    )

    private fun createCommentModel(idTag: Int) = CommentModel(
        id = idTag,
        postId = idTag,
        name = getName(idTag),
        email = getEmail(idTag),
        body = getBody(idTag)
    )

    private fun createCommentList() = mutableListOf<Comment>().apply {
        for (i in 0..LIST_SIZE) add(createComment(i))
    }

    private fun createCommentModelList() = mutableListOf<CommentModel>().apply {
        for (i in 0..LIST_SIZE) add(createCommentModel(i))
    }

    private fun getEmail(idTag: Int) = "$EMAIL$idTag"

    private fun getName(idTag: Int) = "$NAME$idTag"

    private fun getBody(idTag: Int) = "$BODY$idTag"
}
