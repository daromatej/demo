package com.matejdev.demo.domain.usecase

import com.matejdev.demo.base.rx.RxSchedulersFactory
import com.matejdev.demo.base.rx.usecase.SingleUseCase
import com.matejdev.demo.domain.model.UserModel
import com.matejdev.demo.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Use case for fetching all users
 */
class GetUsersUseCase
@Inject constructor(
    rxSchedulersFactory: RxSchedulersFactory,
    private val userRepository: UserRepository
) : SingleUseCase<Void, List<UserModel>>(rxSchedulersFactory) {

    override fun useCase(params: Void?): Single<List<UserModel>> {
        return userRepository.getUsers()
    }
}
