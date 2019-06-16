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
        val comment = stubComment(idTag)

        // when
        val commentModel = mapper.map(comment)

        // then
        verifyCommentModel(idTag, commentModel)
    }

    @Test
    fun `Test comment list is mapper to commentModel list`() {
        // given
        val comments = stubCommentList()

        // when
        val commentModels = mapper.map(comments)

        // then
        verifyCommentModel(commentModels)
    }

    private fun verifyCommentModel(idTag: Int, commentModel: CommentModel) {
        assertEquals("Id should be the same", idTag, commentModel.id)
        assertEquals("PostId should be the same", idTag, commentModel.postId)
        assertEquals("Name should be the same", getName(idTag), commentModel.name)
        assertEquals("Email should be the same", getEmail(idTag), commentModel.email)
        assertEquals("Body should be the same", getBody(idTag), commentModel.body)
    }

    private fun verifyCommentModel(commentModels: List<CommentModel>) {
        for (i in 0..LIST_SIZE) verifyCommentModel(i, commentModels[i])
    }

    private fun stubComment(idTag: Int) = Comment(
        id = idTag,
        postId = idTag,
        name = getName(idTag),
        email = getEmail(idTag),
        body = getBody(idTag)
    )

    private fun stubCommentList() = mutableListOf<Comment>().apply {
        for (i in 0..LIST_SIZE) add(stubComment(i))
    }

    private fun getEmail(idTag: Int) = "$EMAIL$idTag"

    private fun getName(idTag: Int) = "$NAME$idTag"

    private fun getBody(idTag: Int) = "$BODY$idTag"
}
