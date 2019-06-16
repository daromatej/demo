package com.matejdev.demo.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.matejdev.demo.base.view.BaseViewModel
import com.matejdev.demo.domain.model.UserModel
import com.matejdev.demo.domain.usecase.GetUsersUseCase
import com.matejdev.demo.presentation.viewmodel.state.UserListState
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * ViewModel for [com.matejdev.demo.presentation.view.UserListFragment]
 */
class UserListViewModel
@Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : BaseViewModel() {

    val users = MutableLiveData<MutableList<UserModel>>()

    val state = MutableLiveData<UserListState>()

    override fun init() {
        loadUsers()
    }

    private fun loadUsers() {
        state.value = UserListState(showLoader = true)
        getUsersUseCase.execute(UsersObserver())
    }

    inner class UsersObserver : DisposableSingleObserver<List<UserModel>>() {
        override fun onSuccess(items: List<UserModel>) {
            users.value = items.toMutableList()
            state.value = UserListState(showLoader = false)
        }

        override fun onError(e: Throwable) {
            state.value = UserListState(showLoader = false, showError = true)
        }
    }

    override fun onCleared() {
        getUsersUseCase.dispose()
    }
}
