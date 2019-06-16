package com.matejdev.demo.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.matejdev.demo.base.view.BaseViewModel
import com.matejdev.demo.domain.model.PostModel
import com.matejdev.demo.domain.model.UserModel
import com.matejdev.demo.domain.usecase.GetPostsUseCase
import com.matejdev.demo.presentation.viewmodel.state.PostListState
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * ViewModel for [com.matejdev.demo.presentation.view.PostListFragment]
 */
class PostListViewModel
@Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : BaseViewModel() {

    val posts = MutableLiveData<MutableList<PostModel>>()

    val user = MutableLiveData<UserModel>()

    val state = MutableLiveData<PostListState>()

    fun loadPosts(userId: Int) {
        state.value = PostListState(showLoader = true)
        getPostsUseCase.execute(PostsObserver(), userId)
    }

    inner class PostsObserver : DisposableSingleObserver<List<PostModel>>() {
        override fun onSuccess(items: List<PostModel>) {
            posts.value = items.toMutableList()
            state.value = PostListState(showLoader = false)
        }

        override fun onError(e: Throwable) {
            state.value = PostListState(showLoader = false, showError = true)
        }
    }

    override fun onCleared() {
        getPostsUseCase.dispose()
    }
}
