package com.matejdev.demo.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.matejdev.demo.base.view.BaseViewModel
import com.matejdev.demo.domain.model.CommentModel
import com.matejdev.demo.domain.model.PostModel
import com.matejdev.demo.domain.usecase.GetCommentsUseCase
import com.matejdev.demo.presentation.viewmodel.state.CommentListState
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

/**
 * ViewModel for [com.matejdev.demo.presentation.view.CommentListFragment]
 */
class CommentListViewModel
@Inject constructor(
    private val getCommentsUseCase: GetCommentsUseCase
) : BaseViewModel() {

    val comments = MutableLiveData<MutableList<CommentModel>>()

    val post = MutableLiveData<PostModel>()

    val state = MutableLiveData<CommentListState>()

    fun loadComments(userId: Int) {
        state.value = CommentListState(showLoader = true)
        getCommentsUseCase.execute(CommentsObserver(), userId)
    }

    inner class CommentsObserver : DisposableSingleObserver<List<CommentModel>>() {
        override fun onSuccess(items: List<CommentModel>) {
            comments.value = items.toMutableList()
            state.value = CommentListState(showLoader = false)
        }

        override fun onError(e: Throwable) {
            state.value = CommentListState(showLoader = false, showError = true)
        }
    }

    override fun onCleared() {
        getCommentsUseCase.dispose()
    }
}
