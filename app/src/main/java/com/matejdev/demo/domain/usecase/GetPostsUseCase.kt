package com.matejdev.demo.domain.usecase

import com.matejdev.demo.base.rx.RxSchedulersFactory
import com.matejdev.demo.base.rx.usecase.SingleUseCase
import com.matejdev.demo.domain.model.PostModel
import com.matejdev.demo.domain.repository.PostRepository
import io.reactivex.Single
import javax.inject.Inject

class GetPostsUseCase
@Inject constructor(
    rxSchedulersFactory: RxSchedulersFactory,
    private val postRepository: PostRepository
) : SingleUseCase<Int, List<PostModel>>(rxSchedulersFactory) {

    override fun useCase(params: Int?): Single<List<PostModel>> = params?.let {
        postRepository.getPostsForUser(it)
    } ?: Single.error(IllegalArgumentException("Params must not be null"))
}
