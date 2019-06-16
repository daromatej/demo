package com.matejdev.demo.domain.usecase

import com.matejdev.demo.base.rx.RxSchedulersFactory
import com.matejdev.demo.base.rx.usecase.SingleUseCase
import com.matejdev.demo.domain.model.CommentModel
import com.matejdev.demo.domain.repository.CommentRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Use case for fetching all comments for given post
 */
class GetCommentsUseCase
@Inject constructor(
    rxSchedulersFactory: RxSchedulersFactory,
    private val commentRepository: CommentRepository
) : SingleUseCase<Int, List<CommentModel>>(rxSchedulersFactory) {

    override fun useCase(params: Int?): Single<List<CommentModel>> = params?.let {
        commentRepository.getCommentsForPost(it)
    } ?: Single.error(IllegalArgumentException("Params must not be null"))
}
